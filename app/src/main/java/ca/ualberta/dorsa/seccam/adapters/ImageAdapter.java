package ca.ualberta.dorsa.seccam.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.GalleryItem;

public class ImageAdapter {
    private final Context mContext;
    private final List<GalleryItem> photos;

    public ImageAdapter(Context context, List<GalleryItem> photos) {
        this.mContext = context;
        this.photos = photos;
    }

    public int getCount() {
        return photos.size();
    }

    public long getItemId(int position) {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.linearlayout_image, parent, false);
        }
        final Bitmap bitmap = this.photos.get(position).getPhoto();
        final ImageView imageView = convertView.findViewById(R.id.imageview);
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
