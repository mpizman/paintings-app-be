package com.matan.paintings.services.implementations;

import com.matan.paintings.models.implemenatations.ERole;
import com.matan.paintings.models.interfaces.IPaintingDTO;
import com.matan.paintings.repository.PaintingRepository;
import com.matan.paintings.services.interfaces.IDeletePaintingService;
import com.matan.paintings.services.interfaces.IGetPaintingByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.FileNotFoundException;

public class DeletePaintingService implements IDeletePaintingService {
    @Autowired
    PaintingRepository paintingRepository;

    @Autowired
    IGetPaintingByIdService getPaintingByIdService;

    @Override
    public String deletePaintingService(String id) throws FileNotFoundException {
        IPaintingDTO paintingDTO = getPaintingByIdService.execute(id);
        if (paintingDTO == null) {
            throw new FileNotFoundException();
        }
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (!currentAuthentication.getPrincipal().equals(paintingDTO.getUploaderUsername()) && currentAuthentication.getAuthorities().stream().noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ERole.ROLE_ADMIN.name()))) { //or admin!!
            throw new InsufficientAuthenticationException("user is not allowed to delete painting");
        }
        paintingRepository.deleteById(id);
        return id;
    }
}
