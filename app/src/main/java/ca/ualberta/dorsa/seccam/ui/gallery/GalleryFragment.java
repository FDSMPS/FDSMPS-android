package ca.ualberta.dorsa.seccam.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.security.crypto.EncryptedFile;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.activities.SlideShowActivity;
import ca.ualberta.dorsa.seccam.adapters.ImageAdapter;
import ca.ualberta.dorsa.seccam.entities.GalleryItem;

import static ca.ualberta.dorsa.seccam.activities.FullscreenIntroActivity.masterKeyAlias;

/**
 * The gallery fragment
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class GalleryFragment extends Fragment {

    /**
     * The Photos.
     */
    public static List<GalleryItem> photos;
    /**
     * The constant PHOTO_CONTENT.
     */
    public static final String PHOTO_CONTENT = "ca.ualberta.dorsa.seccam.photos";
    /**
     * The constant FILE_NAME.
     */
    public static final String FILE_NAME = "ca.ualberta.dorsa.seccam.file.name";
    /**
     * The constant GALLERY_MODE.
     */
    public static final String GALLERY_MODE = "ca.ualberta.dorsa.seccam.gallerymode";
    private ImageAdapter imageAdapter;
    private GridView gridView;
    /**
     * The Slow loader.
     */
    AsyncTask<Void, Void, Void> slowLoader;


    /**
     * Instantiates a new Gallery fragment.
     */
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

        if (slowLoader != null)
            slowLoader.cancel(true);
        slowLoader = new LongOperation();
        slowLoader.execute();

        gridView = view.findViewById(R.id.fragment_full_gallery_gridview);
        imageAdapter = new ImageAdapter(getActivity(), photos);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity().getBaseContext(),
                    SlideShowActivity.class);
            intent.putExtra(PHOTO_CONTENT, position);
            getActivity().startActivity(intent);
        });

        gridView.setOnItemLongClickListener((parent, view12, position, id) -> {
            File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + photos.get(position).getImageIndex());
            file.delete();
            if (file.exists()) {
                getActivity().deleteFile(file.getName());
            } else {
                photos.remove(position);
                imageAdapter.notifyDataSetChanged();
            }
            return true;
        });

        return view;
    }


    private void loadGallery() {
        File folder = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        File f = new File(folder.getAbsolutePath());
        File[] file = f.listFiles();
        if (photos.size() != file.length) {
            for (File value : file) {
                if (value.exists()) {
                    try {
                        EncryptedFile encryptedFile = new EncryptedFile.Builder(
                                new File(folder.getAbsolutePath(), value.getName()),
                                getContext(),
                                masterKeyAlias,
                                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
                        ).build();
                        FileInputStream encryptedInputStream = encryptedFile.openFileInput();
                        int content;
                        StringBuilder base64Image = new StringBuilder();
                        while ((content = encryptedInputStream.read()) != -1) {
                            base64Image.append((char) content);
                        }
                        photos.add(new GalleryItem(value.getName(), StringToBitMap(base64Image.toString())));
                    } catch (IOException | GeneralSecurityException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    /**
     * creates a bit map from a given string
     */
    private Bitmap StringToBitMap(String image) {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    private final class LongOperation extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            loadGallery();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            imageAdapter.notifyDataSetChanged();
        }
    }

}