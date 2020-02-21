package ca.ualberta.dorsa.seccam.entities;

import android.graphics.Bitmap;

public class GalleryItem {
    private String problemIndex;
    private Bitmap photo;

    public String getProblemIndex() {
        return problemIndex;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public GalleryItem(String problemIndex, Bitmap photo) {
        this.problemIndex = problemIndex;
        this.photo = photo;
    }
}
