package ca.ualberta.dorsa.seccam.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import ca.ualberta.dorsa.seccam.R;

/**
 * The type Settings fragment.
 * Executed UI tested yet to be unit tested
 *
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020 -1-31
 * Project: ECE 492 Group 1
 */
public class GalleryFragment extends Fragment {

    private GalleryViewModel settingsViewModel;
    private TextView qrCode;
    private TextView propertyAddress;
    private SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        qrCode = root.findViewById(R.id.qr_code);
        propertyAddress = root.findViewById((R.id.owner_coordinates));
        sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return root;
    }




}