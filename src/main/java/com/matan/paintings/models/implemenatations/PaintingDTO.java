package com.matan.paintings.models.implemenatations;

import com.matan.paintings.models.interfaces.IPaintingDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
public class PaintingDTO implements IPaintingDTO {
    @Id
    private String id;
    @TextIndexed(weight=2)
    @NotBlank
    private String name;
    @TextIndexed
    private String description;
    private String url;
    private Date date;
    private String artist;
    private double price;
    @TextScore
    private Float score;
    @NotNull
    private String uploaderUsername;

    public PaintingDTO() {

    }

    public PaintingDTO(String name, String description, String url, Date date, String artist, double price) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.date = date;
        this.artist = artist;
        this.price = price;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    public String getArtist() {
        return artist;
    }

    public double getPrice() {
        return price;
    }

    public String getUploaderUsername() {
        return uploaderUsername;
    }

    public void setUploaderUsername(String uploaderUsername) {
        this.uploaderUsername = uploaderUsername;
    }
}
