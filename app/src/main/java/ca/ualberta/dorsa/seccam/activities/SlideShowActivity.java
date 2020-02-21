package ca.ualberta.dorsa.seccam.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.adapters.SlidingImageAdaptor;

public class SlideShowActivity extends AppCompatActivity {

    public static final String PHOTO_CONTENT = "ca.ualberta.dorsa.seccam.photos";
    private static ViewPager mPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            int position = getIntent().getIntExtra(PHOTO_CONTENT,0);
            init(position);
        }
    }

    private void init(int currentItem) {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdaptor(SlideShowActivity.this));
        mPager.setCurrentItem(currentItem);
    }
}