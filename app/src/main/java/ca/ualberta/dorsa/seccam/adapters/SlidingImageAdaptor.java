package ca.ualberta.dorsa.seccam.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.ui.gallery.GalleryFragment;

/**
 * The type Slide show activity. allows for scrolling through the images
 * Executed UI tested yet to be unit tested
 *
 * @author Dorsa Nahid
 * @date 2020 -2-21 Project: ECE 492 Group 1
 */
public class SlidingImageAdaptor extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;


    /**
     * Instantiates a new Sliding image adaptor.
     *
     * @param context the context
     */
    public SlidingImageAdaptor(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public int getCount() {
        return GalleryFragment.photos.size();
    }

    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


        imageView.setImageBitmap(GalleryFragment.photos.get(position).getPhoto());

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    public Parcelable saveState() {
        return null;
    }
}
