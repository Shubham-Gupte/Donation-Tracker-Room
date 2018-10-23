package roomies.donationtracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsViewAdapter extends RecyclerView.Adapter<ItemsViewAdapter.ViewHolder> {

    // Variables
    private ArrayList<Item> itemsList;

    public ItemsViewAdapter(ArrayList<Item> items) {
        itemsList = items;
    }

    // Take the cell view from layout_location_cellon_cell.xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_cell, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Set the views in each cell for the location
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemNameView.setText(itemsList.get(i).getName());
        viewHolder.itemTypeView.setText(itemsList.get(i).getType());
        viewHolder.itemCostView.setText(Float.toString(itemsList.get(i).getCost()));
        viewHolder.itemDateDonatedView.setText(itemsList.get(i).getDonationDate());
    }

    // Count how many cells the table needs
    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    // The view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView;
        TextView itemTypeView;
        TextView itemCostView;
        TextView itemDateDonatedView;
        public ViewHolder(View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.itemNameId);
            itemTypeView = itemView.findViewById(R.id.itemType);
            itemCostView = itemView.findViewById(R.id.itemCostId);
            itemDateDonatedView = itemView.findViewById(R.id.dateDonatedId);
        }
    }
}
