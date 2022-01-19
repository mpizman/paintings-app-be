package com.matan.paintings.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.jayway.jsonpath.JsonPath;
import com.matan.paintings.constants.Constants;
import com.matan.paintings.models.implemenatations.ERole;
import com.matan.paintings.models.implemenatations.PaintingDTO;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IGetPaintingByIdService;
import com.matan.paintings.services.interfaces.IPatchPaintingService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PatchPaintingService implements IPatchPaintingService {
    @Autowired
    IGetPaintingByIdService getPaintingByIdService;

    @Autowired
    PaintingRepository paintingRepository;

    @Override
    public IPaintingDTO execute(String id, JsonPatch patchElements) throws FileNotFoundException {
        IPaintingDTO paintingDTO = getPaintingByIdService.execute(id);
        if (paintingDTO == null) {
            throw new FileNotFoundException();
        }
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (!currentAuthentication.getPrincipal().equals(paintingDTO.getUploaderUsername()) && currentAuthentication.getAuthorities().stream().noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ERole.ROLE_ADMIN.name()))) { //or admin!!
            throw new InsufficientAuthenticationException("user is not allowed to change painting");
        }
        try {
            IPaintingDTO patchPainting = applyPatchToPainting(patchElements, paintingDTO);
            return paintingRepository.save((PaintingDTO) patchPainting);
        } catch (RuntimeException runtimeException) {
            throw runtimeException;
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }

    private IPaintingDTO applyPatchToPainting(JsonPatch jsonPatch, IPaintingDTO paintingDTO)
            throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> forbiddenValues = checkForbiddenValues(jsonPatch);
        if(!forbiddenValues.isEmpty()) {
            throw new RuntimeException("Changing " + forbiddenValues + " is not allowed");
        }
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(paintingDTO, JsonNode.class));
        return objectMapper.treeToValue(patched, PaintingDTO.class);
    }

    private List<String> checkForbiddenValues(JsonPatch jsonPatch) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(jsonPatch);
        JSONArray parsedJson = JsonPath.parse(json).read("$[*].path");
        List<String> forbiddenPatchFieldsPath = new ArrayList<>();
        for(Object path: parsedJson) {
            for (String field: Constants.PAINTING_FIELDS_NOT_UPDATABLE) {
                if(((String) path).contains(field)) {
                    forbiddenPatchFieldsPath.add(field);
                }
            }
        }
        return forbiddenPatchFieldsPath;
    }
}
