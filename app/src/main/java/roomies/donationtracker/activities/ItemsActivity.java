package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roomies.donationtracker.adapters.ItemsViewAdapter;
import roomies.donationtracker.models.Item;
import roomies.donationtracker.models.Location;

/**
 * presents the overall items list for all locations. Allows user to filter items
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 */
public class ItemsActivity extends Activity {

    /**
     *items list displayed, and location currently being viewed
     */
    ArrayList<Item> itemsList = new ArrayList<>();
    ArrayList<Item> allItemsList = new ArrayList<>();
    Location location = null;
    String locationID = null;


    /**
     * creates ItemsActivity for the first time and instantiates view
     *
     * @param savedInstanceState
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        initAddItemButton();
        getIncomingIntent();
        EditText searchField = findViewById(R.id.searchBarId);
        //checks to see if somebody puts stuff into the textbox
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * filters items based on user input
     *
     * @param s string inputted by the user - desired category or locatino
     */
    private void filter(String s) {
        ArrayList<Item> filteredItems = new ArrayList<>();

        ToggleButton category = findViewById(R.id.categoryButtonId);
        if (category.isChecked()) { //if category is checked it means searching by type
            for (Item originalItem: allItemsList) {
                if (originalItem.getType().toLowerCase().contains(s.toLowerCase())) {
                    filteredItems.add(originalItem);
                }
            }
        } else { //category not checked meaning search by name
            for (Item originalItem: allItemsList) {
                if (originalItem.getName().toLowerCase().contains(s.toLowerCase())) {
                    filteredItems.add(originalItem);
                }
            }
        }
        //updates the recycler view
        itemsList = filteredItems;
        initItemsView();
    }

    /**
     * initializes view every time page is opened.
     * @return void.
     */
    @Override
    protected void onStart() {
        super.onStart();
        getIncomingIntent();
    }

    /**
     * Gets the location ID sent with the intent
     *
     * @return void
     */

    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
            getLocationItemsFromDB(locationID);
        } else {
            getAllItemsFromDB();
        }
    }

    /**
     * initializes the add item button, which allows viewer to add an item to their preferred
     * location by taking the viewer to the AddItemActivity class
     * @return void
     */
    private void initAddItemButton() {
        Button addItemButton = findViewById(R.id.addItemButtonID2);
        addItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemsActivity.this, AddItemActivity.class);
                i.putExtra("location_ID", locationID);
                startActivity(i);
            }
        });
    }

    /**
     * creates the recycler view with all items
     * @return void
     */
    private void initItemsView() {
        RecyclerView itemsView = findViewById(R.id.recyclerViewID);
        ItemsViewAdapter itemsViewAdapter = new ItemsViewAdapter(itemsList);
        itemsView.setAdapter(itemsViewAdapter);
        itemsView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Initialize the details for a specific location if the viewer has selected one
     *
     * @return void
     */
    private void initLocationDetails() {
        TextView nameLabel = findViewById(R.id.nameLableId);
        nameLabel.setText(location.getLocationName());

        TextView typeLabel = findViewById(R.id.typeLableId);
        typeLabel.setText(location.getLocationType());

        TextView addressLabel = findViewById(R.id.addressLableId);
        addressLabel.setText(location.getAddress());

        TextView phoneLabel = findViewById(R.id.phoneLableId);
        phoneLabel.setText(location.getPhoneNumber());

        TextView longLabel = findViewById(R.id.longLableId);
        longLabel.setText(String.valueOf(location.getLongitude()));

        TextView latLabel = findViewById(R.id.latLableId);
        latLabel.setText(String.valueOf(location.getLatitude()));

    }


    /**
     * Gets the locations from firebase and initializes the locations view
     * @param locationID the location the viewer wants to see items from
     */
    private void getLocationItemsFromDB(String locationID) {
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childReference = mainDatabase.child("locations").child(locationID)
                .child("Items");

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemsList = new ArrayList<>();
                // Iterate through data from database
                for (DataSnapshot x : dataSnapshot.getChildren()) {
//                    System.out.println(x.child("name").getValue().toString());
//                    System.out.println(x.child("cost").getValue().toString());
//                    System.out.println(x.child("donationDate").getValue().toString());
//                    System.out.println(x.child("donationLocation").getValue().toString());
//                    System.out.println(x.child("type").getValue().toString());

                    // Create a new item object from database data
                    String name = x.child("name").getValue().toString();
                    String type = x.child("type").getValue().toString();
                    double cost;
                    Object costObject = x.child("cost").getValue();
                    if ( costObject instanceof Long) {
                        cost = ((Long) costObject).doubleValue();
                    } else {
                        cost = (double) costObject;
                    }
                    String donationDate = x.child("donationDate").getValue().toString();
                    String donationLocation = x.child("donationLocation").getValue().toString();

                    Item item = new Item(name, type, cost, donationDate, donationLocation);

                    //Add new item to item list
                    allItemsList.add(item);
                }
                // Initialize the locations view table
                itemsList = allItemsList;
                initItemsView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    /**
     *     Gets the locations from firebase and items from each of those locations
     *     Calls init item view
     *     @return void
     */
    private void getAllItemsFromDB() {
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childReference = mainDatabase.child("locations");

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemsList = new ArrayList<>();
                // Iterate through data from database
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                        DataSnapshot items = x.child("Items");
                    for (DataSnapshot y : items.getChildren()) {
                        // Create a new item object from database data
                        String name = y.child("name").getValue().toString();
                        String type = y.child("type").getValue().toString();
                        double cost;
                        Object costObject = y.child("cost").getValue();
                        if ( costObject instanceof Long) {
                            cost = ((Long) costObject).doubleValue();
                        } else {
                            cost = (double) costObject;
                        }
                        String donationDate = y.child("donationDate").getValue().toString();
                        String donationLocation = y.child("donationLocation").getValue().toString();

                        Item item = new Item(name, type, cost, donationDate, donationLocation);

                        //Add new item to item list
                        allItemsList.add(item);
                    }
                }

                // Initialize the locations view table
                itemsList = allItemsList;
                initItemsView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}
