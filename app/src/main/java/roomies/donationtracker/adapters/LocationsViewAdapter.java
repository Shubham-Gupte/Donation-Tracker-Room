package roomies.donationtracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import roomies.donationtracker.activities.LocationDetailsActivity;
import roomies.donationtracker.activities.R;
import roomies.donationtracker.models.Location;

public class LocationsViewAdapter extends RecyclerView.Adapter<LocationsViewAdapter.ViewHolder> {

    // Variables
    private ArrayList<Location> locationsList;
    private Context context;

    /**
     * Creates a new Location View adapter
     * @param context the context
     * @param locations list of locations
     */
    public LocationsViewAdapter(Context context, ArrayList<Location> locations) {
        locationsList = locations;
        this.context = context;
    }

    /**
     * Take the cell view from layout_location_cellon_cell.xml
     * @param viewGroup The view group
     * @param i integer
     * @return new viewholder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_location_cell, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Set the views in each cell for the location
     * @param viewHolder The viewholder
     * @param i integer
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.locationNameView.setText(locationsList.get(i).getLocationName());
        viewHolder.locationAddressView.setText(locationsList.get(i).getAddress());
        viewHolder.locationNameView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocationDetailsActivity.class);
                intent.putExtra("location_ID", locationsList.get(i).getLocationID());
                context.startActivity(intent);
            }
        });
    }

    /**
     * Count how many cells the table needs
     * @return the count of the items
     */
    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    // The view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView locationNameView;
        TextView locationAddressView;
        public ViewHolder(View itemView) {
            super(itemView);
            locationNameView = itemView.findViewById(R.id.itemNameId);
            locationAddressView = itemView.findViewById(R.id.addressID);
        }
    }
}
