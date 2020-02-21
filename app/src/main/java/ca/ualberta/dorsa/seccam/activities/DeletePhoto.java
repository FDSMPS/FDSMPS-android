package ca.ualberta.dorsa.seccam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.ui.gallery.GalleryFragment;

public class DeletePhoto extends AppCompatActivity {
    public static final String PHOTO_CONTENT = "ca.ualberta.dorsa.seccam.photos";
    public static final String FILE_NAME = "ca.ualberta.dorsa.seccam.file.name";

    ImageView delete_image;
    private int position;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_photo);
        delete_image = findViewById(R.id.imageView_delete_photo);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = getIntent().getIntExtra(PHOTO_CONTENT, 0);
            fileName = getIntent().getStringExtra(FILE_NAME);
            delete_image.setImageBitmap(GalleryFragment.photos.get(position).getPhoto());

        }

//        opens an intent, grab the picture and then put it in the next function
    }

    public void deleteImage(View view) {
        File file = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+File.separator+fileName);
        Log.d("DELETE_FILE",this.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+File.separator+fileName);
        file.delete();
        if (file.exists()) {
            getApplicationContext().deleteFile(file.getName());
        }else{
            finish();
        }

    }
}
