package ca.ualberta.dorsa.seccam.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.activities.DeletePhoto;
import ca.ualberta.dorsa.seccam.activities.SlideShowActivity;
import ca.ualberta.dorsa.seccam.adapters.GalleryAdapter;
import ca.ualberta.dorsa.seccam.adapters.ImageAdapter;
import ca.ualberta.dorsa.seccam.entities.GalleryItem;

/**
 * The type Settings fragment.
 * Executed UI tested yet to be unit tested
 *
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020 -1-31
 * Project: ECE 492 Group 1
 */
public class GalleryFragment extends Fragment {


    public static final String PHOTO_CONTENT = "ca.cybersix.photo";
    public static final String GALLERY_MODE = "ca.cybersix.gallerymode";


    public static List<GalleryItem> photos;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        photos = new ArrayList<GalleryItem>();
        int size;

        if(getArguments()!=null) {
            int problemIndex = getArguments().getInt(GALLERY_MODE);
//            if (RecordController.getInstance().getSelectedProblemRecords().get(problemIndex) == null) {
//                size = 0;
//            } else {
//                size = RecordController.getInstance().getSelectedProblemRecords().get(problemIndex).getPhotos().size();
//            }
//
//            for (int i = 0; i < size; i++) {
//                photos.add(new GalleryItem(problemIndex,RecordController.getInstance().getSelectedProblemRecords().get(problemIndex).getPhotos().get(i)));
//            }
//
//        }else{
//            if(ProblemController.getInstance().getSelectedProblem()!=null) {
//                if (RecordController.getInstance().getSelectedProblemRecords() == null) {
//                    size = 0;
//                } else {
//                    size = RecordController.getInstance().getSelectedProblemRecords().size();
//                }
//
//                for (int i = 0; i < size; i++) {
//                    int recordSize = RecordController.getInstance().getSelectedProblemRecords().get(i).getPhotos().size();
//                    for (int j = 0; j < recordSize; j++) {
//                        photos.add(new GalleryItem(i, RecordController.getInstance().getSelectedProblemRecords().get(i).getPhotos().get(j)));
//                    }
//                }
//            }
//        }


        GridView gridView = view.findViewById(R.id.fragment_full_gallery_gridview);
        final ImageAdapter imageAdapter = new ImageAdapter(getActivity(), photos);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity().getBaseContext(),
                    SlideShowActivity.class);
            intent.putExtra(PHOTO_CONTENT, position);
            getActivity().startActivity(intent);
        });

        gridView.setOnItemLongClickListener((parent, view12, position, id) -> {
            Intent intent = new Intent(getActivity().getBaseContext(),
                    DeletePhoto.class);
            intent.putExtra(PHOTO_CONTENT, position);
            getActivity().startActivity(intent);
            return true;
        });

//        TextView title = getActivity().findViewById(R.id.fragment_title_bar_fragmentTitle);
//        View returnButton = getActivity().findViewById(R.id.fragment_title_bar_returnButton);

        return view;
    }
}




}