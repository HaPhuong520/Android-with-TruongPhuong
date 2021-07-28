package com.example.imagelikeinstagram;

import java.util.List;

public class ListPhoto {
    private List<PhoTo> list;

    public ListPhoto(List<PhoTo> list) {
        this.list = list;
    }

    public List<PhoTo> getList() {
        return list;
    }

    public void setList(List<PhoTo> list) {
        this.list = list;
    }
}
