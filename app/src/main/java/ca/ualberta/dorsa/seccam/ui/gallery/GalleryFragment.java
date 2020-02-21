package ca.ualberta.dorsa.seccam.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.activities.DeletePhoto;
import ca.ualberta.dorsa.seccam.activities.SlideShowActivity;
import ca.ualberta.dorsa.seccam.adapters.ImageAdapter;
import ca.ualberta.dorsa.seccam.entities.GalleryItem;

public class GalleryFragment extends Fragment {

    public static List<GalleryItem> photos;
    public static final String PHOTO_CONTENT = "ca.ualberta.dorsa.seccam.photos";
    public static final String FILE_NAME = "ca.ualberta.dorsa.seccam.file.name";
    public static final String GALLERY_MODE = "ca.ualberta.dorsa.seccam.gallerymode";
    public ImageAdapter imageAdapter;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        photos = new ArrayList<GalleryItem>();
        loadGallery();


        GridView gridView = view.findViewById(R.id.fragment_full_gallery_gridview);
        imageAdapter = new ImageAdapter(getActivity(), photos);
        gridView.setAdapter(imageAdapter);


        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity().getBaseContext(),
                    SlideShowActivity.class);
            intent.putExtra(PHOTO_CONTENT, position);
            getActivity().startActivity(intent);
        });

        gridView.setOnItemLongClickListener((parent, view12, position, id) -> {
            File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+File.separator+photos.get(position).getProblemIndex());
            file.delete();
            if (file.exists()) {
                getActivity().deleteFile(file.getName());
            }else{
                photos.remove(position);
                this.imageAdapter.notifyDataSetChanged();
            }
            return true;
        });
//        gridView.setOnItemLongClickListener((parent, view12, position, id) -> {
//            Intent intent = new Intent(getActivity().getBaseContext(),
//                    DeletePhoto.class);
//            intent.putExtra(FILE_NAME, photos.get(position).getProblemIndex());
//            intent.putExtra(PHOTO_CONTENT, position);
//            getActivity().startActivity(intent);
//            return true;
//        });

        return view;
    }


    private void loadGallery() {
        File folder = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        File f = new File(folder.getAbsolutePath());
        File[] file = f.listFiles();
        for (File value : file) {
            if (value.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(value.getAbsolutePath());
                photos.add(new GalleryItem(value.getName(), myBitmap));
            }
        }
    }

}