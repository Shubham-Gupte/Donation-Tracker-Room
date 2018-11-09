package roomies.donationtracker.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import roomies.donationtracker.activities.R;
import roomies.donationtracker.models.Item;

public class ItemsViewAdapter extends RecyclerView.Adapter<ItemsViewAdapter.ViewHolder> {

    // Variables
    private List<Item> itemsList;

    public ItemsViewAdapter(ArrayList<Item> items) {
        itemsList = items;
    }

    // Take the cell view from layout_location_cellon_cell.xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_cell,
                viewGroup, false);
        return new ViewHolder(view);
    }

    // Set the views in each cell for the location
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Item item = itemsList.get(i);
        viewHolder.itemNameView.setText(item.getName());
        viewHolder.itemTypeView.setText(item.getType());
        viewHolder.itemCostView.setText(Double.toString(item.getCost()));
        viewHolder.itemDateDonatedView.setText(item.getDonationDate());
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
            itemTypeView = itemView.findViewById(R.id.itemTypeId);
            itemCostView = itemView.findViewById(R.id.itemCostId);
            itemDateDonatedView = itemView.findViewById(R.id.dateDonatedId);
        }
    }

    public void filterList(ArrayList<Item> filteredList) {
        itemsList = filteredList;
        notifyDataSetChanged();
    }
}
