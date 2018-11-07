package roomies.donationtracker.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import roomies.donationtracker.activities.LocationFragment.OnListFragmentInteractionListener;
import roomies.donationtracker.activities.dummy.DummyContent.DummyItem;
import roomies.donationtracker.models.Location;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Location} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class MyLocationRecyclerViewAdapter extends RecyclerView.Adapter<MyLocationRecyclerViewAdapter.ViewHolder> {

    /**
     * list of locations
     */
    private final List<Location> mValues;
    private final OnListFragmentInteractionListener mListener;

    /**
     * constructor that takes a list of items and listener
     * @param items a list of items
     * @param listener listener that waits for interaction with fragment
     */
    public MyLocationRecyclerViewAdapter(List<Location> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * creates view holder for recycler
     *
     * @param parent parent view group
     * @param viewType type of view
     * @return ViewHolder the view with the new recycler added
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_location, parent, false);
        return new ViewHolder(view);
    }

    /**
     * sets text in holder and sets on click listener
     * @param holder view holder
     * @param position position in view
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getLocationName());
        holder.mContentView.setText(mValues.get(position).getPhoneNumber());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * get number of items
     * @return number
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * viewHolder, which extends RecyclerView
     * @author java
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * text and views
         */
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Location mItem;

        /**
         * constructor
         * @param view current view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        /**
         * to string method
         * @return Strings
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
