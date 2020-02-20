package ca.ualberta.dorsa.seccam.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.ui.gallery.GalleryFragment;

public class DeletePhoto extends AppCompatActivity {
    public static final String PHOTO_CONTENT = "ca.ualberta.dorsa.seccam.photos";
    ImageView delete_image;
    private int position;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_photo);
        delete_image = findViewById(R.id.imageView_delete_photo);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            position = getIntent().getIntExtra(PHOTO_CONTENT,0);
            delete_image.setImageBitmap(GalleryFragment.photos.get(position).getPhoto());

        }

//        opens an intent, grab the picture and then put it in the next function
    }

    public void deleteImage(View view) {

    }
}
