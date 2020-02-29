package ca.ualberta.dorsa.seccam.entities;

import android.graphics.Bitmap;

/**
 * The type is a gallery item
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class GalleryItem {
    private String imageIndex;
    private Bitmap photo;

    /**
     * Gets image index.
     *
     * @return the image index
     */
    public String getImageIndex() {
        return imageIndex;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public Bitmap getPhoto() {
        return photo;
    }

    /**
     * Instantiates a new Gallery item.
     *
     * @param imageIndex the image index
     * @param photo      the photo
     */
    public GalleryItem(String imageIndex, Bitmap photo) {
        this.imageIndex = imageIndex;
        this.photo = photo;
    }
}
