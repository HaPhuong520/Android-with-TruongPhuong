package com.example.imagelikeinstagram;

import java.util.List;


public class ListPhoto {
    private List<PhoTo> photos;
    private int Type;

    public ListPhoto(List<PhoTo> photos, int type) {
        this.photos = photos;
        Type = type;
    }

    public List<PhoTo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhoTo> photos) {
        this.photos = photos;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
