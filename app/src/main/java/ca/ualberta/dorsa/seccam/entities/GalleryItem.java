package ca.ualberta.dorsa.seccam.entities;

import android.graphics.Bitmap;

public class GalleryItem {
    private String imageIndex;
    private Bitmap photo;

    public String getImageIndex() {
        return imageIndex;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public GalleryItem(String imageIndex, Bitmap photo) {
        this.imageIndex = imageIndex;
        this.photo = photo;
    }
}
