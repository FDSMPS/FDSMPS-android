package ca.ualberta.dorsa.seccam.ui.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.security.keystore.KeyGenParameterSpec;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.activities.SlideShowActivity;
import ca.ualberta.dorsa.seccam.adapters.ImageAdapter;
import ca.ualberta.dorsa.seccam.entities.GalleryItem;

public class GalleryFragment extends Fragment {

    public static List<GalleryItem> photos;
    public static final String PHOTO_CONTENT = "ca.ualberta.dorsa.seccam.photos";
    public static final String FILE_NAME = "ca.ualberta.dorsa.seccam.file.name";
    public static final String GALLERY_MODE = "ca.ualberta.dorsa.seccam.gallerymode";
    public ImageAdapter imageAdapter;

    KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
    String masterKeyAlias;

    {
        try {
            masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        try {
            loadGallery();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
            File file = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+File.separator+photos.get(position).getImageIndex());
            file.delete();
            if (file.exists()) {
                getActivity().deleteFile(file.getName());
            }else{
                photos.remove(position);
                this.imageAdapter.notifyDataSetChanged();
            }
            return true;
        });
        return view;
    }


    private void loadGallery() throws GeneralSecurityException, IOException {
        File folder = new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        File f = new File(folder.getAbsolutePath());
        File[] file = f.listFiles();
        for (File value : file) {
            if (value.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(value.getAbsolutePath());
                //TODO decode the encryption

                EncryptedFile encryptedFile = new EncryptedFile.Builder(
                        new File(folder, value.getName()),
                        getContext(),
                        masterKeyAlias,
                        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
                ).build();

                StringBuffer stringBuffer = new StringBuffer();
                try (BufferedReader reader =
                             new BufferedReader(new FileReader(String.valueOf(encryptedFile)))) {

                    String line = reader.readLine();
                    while (line != null) {
                        stringBuffer.append(line).append('\n');
                        line = reader.readLine();
                    }
                } catch (IOException e) {
                    // Error occurred opening raw file for reading.
                } finally {
                    String contents = stringBuffer.toString();
                }
                photos.add(new GalleryItem(value.getName(), myBitmap));
            }
        }
    }

}