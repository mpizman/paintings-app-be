package com.matan.paintings.painting;

import java.util.Date;

public interface IPainting {
    void setName(String name);
    void setId(String id);
    void setDescription(String description);
    void setUrl(String url);
    void setDate(Date date);
    void setArtist(String artist);
    void setPrice(double price);
    String getName();
    String getDescription();
    String getUrl();
    Date getDate();
    String getArtist();
    double getPrice();
    String getId();
}
