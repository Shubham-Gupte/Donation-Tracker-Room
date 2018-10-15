package roomies.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationsViewAdapter extends RecyclerView.Adapter<LocationsViewAdapter.ViewHolder> {

    // Variables
    private ArrayList<Location> locationsList;

    public LocationsViewAdapter(ArrayList<Location> locations) {
        locationsList = locations;
    }

    // Take the cell view from layout_location_cellon_cell.xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_location_cell, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Set the views in each cell for the location
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.locationNameView.setText(locationsList.get(i).getLocationName());
        viewHolder.locationAddressView.setText(locationsList.get(i).getAddress());
    }

    // Count how many cells the table needs
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
            locationNameView = itemView.findViewById(R.id.locationNameId);
            locationAddressView = itemView.findViewById(R.id.addressID);
        }
    }
}