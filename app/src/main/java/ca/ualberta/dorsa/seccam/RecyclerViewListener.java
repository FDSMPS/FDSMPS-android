package ca.ualberta.dorsa.seccam;

import android.view.View;

/**
 * The interface Recycler view listener.
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
