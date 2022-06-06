package com.example.wallpaper2_app;

import java.util.ArrayList;

public class PhotosModel {

    private ArrayList<SrcModel> photos;

    public PhotosModel(ArrayList<SrcModel> photos) {
        this.photos = photos;
    }

    public ArrayList<SrcModel> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<SrcModel> photos) {
        this.photos = photos;
    }
}
