package ca.ualberta.dorsa.seccam;

import android.view.View;

/**
 * The interface Recycler view listener.
 *
 * @author Jessica D'Cunha and Dorsa Nahid
 * @date 2020-1-31
 * Project: ECE 492 Group 1
 */
public interface RecyclerViewListener {
    /**
     * Recycler view on long click.
     *
     * @param view     the view
     * @param position the position
     */
    void recyclerViewOnLongClick(View view, int position);
}
