package ca.ualberta.dorsa.seccam.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.activities.SlideShowActivity;
import ca.ualberta.dorsa.seccam.entities.GalleryItem;

/**
 * The type gallery adapter, adapt data to visualization
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class GalleryAdapter extends Fragment {

    /**
     * The constant PHOTO_CONTENT.
     */
    public static final String PHOTO_CONTENT = " ca.ualberta.dorsa.seccam.photos";


    /**
     * The Photos.
     */
    public static List<GalleryItem> photos;

    /**
     * Instantiates a new Gallery adapter.
     */
    public GalleryAdapter() {
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

        GridView gridView = view.findViewById(R.id.fragment_full_gallery_gridview);
        final ImageAdapter imageAdapter = new ImageAdapter(getActivity(), photos);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity().getBaseContext(),
                    SlideShowActivity.class);
            intent.putExtra(PHOTO_CONTENT, position);
            getActivity().startActivity(intent);
        });


        return view;
    }

}
