package com.matan.paintings.painting;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Painting implements IPainting{
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private String url;
    private Date date;
    private String artist;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Painting(String name, String description, String url, Date date, String artist, double price) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.date = date;
        this.artist = artist;
        this.price = price;
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
}
