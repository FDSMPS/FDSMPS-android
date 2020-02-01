package ca.ualberta.dorsa.seccam.ui.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ca.ualberta.dorsa.seccam.R;

/**
 * The type Alerts fragment. It would include a list of previous alert
 * @author Jessica D'Cunha
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public class AlertsFragment extends Fragment {

    private AlertsViewModel alertsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alertsViewModel =
                ViewModelProviders.of(this).get(AlertsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alerts, container, false);
        final TextView textView = root.findViewById(R.id.text_alerts);
        alertsViewModel.getText().observe(this, s -> textView.setText(s));
        return root;
    }
}