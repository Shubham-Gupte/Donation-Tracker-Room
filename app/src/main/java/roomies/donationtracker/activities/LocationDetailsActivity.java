package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roomies.donationtracker.adapters.ItemsViewAdapter;
import roomies.donationtracker.models.Item;
import roomies.donationtracker.models.Location;


public class LocationDetailsActivity extends AppCompatActivity {

    // Map to use for creating location list
    ArrayList<Item> itemsList = new ArrayList<>();
    Location location = null;
    String locationID = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        initDoneButton();
        initSearchButton();
        initAddItemButton();
        getIncomingIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getIncomingIntent();
    }

    // Gets the location ID sent with the intent
    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
            getLocationsFromDB();
            getLocationItemsFromDB(locationID);
        }
    }

    // Creates the logout button
    private void initDoneButton() {
        Button doneButton = findViewById(R.id.doneButton_ID);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationDetailsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    // Creates the search list
    private void initSearchButton() {
        Button doneButton = findViewById(R.id.searchButtonID);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationDetailsActivity.this, searchActivity.class);
                i.putExtra("location_ID", locationID);
                i.putExtra("itemslist", itemsList);
                startActivity(i);
            }
        });
    }
    private void initAddItemButton() {
        Button doneButton = findViewById(R.id.addItemButtonID);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationDetailsActivity.this, AddItemActivity.class);
                i.putExtra("location_ID", locationID);
                startActivity(i);
            }
        });
    }

    // Creates the locations view
    private void initItemsView() {
        RecyclerView itemsView = findViewById(R.id.recyclerViewID);
        ItemsViewAdapter itemsViewAdapter = new ItemsViewAdapter(itemsList);
        itemsView.setAdapter(itemsViewAdapter);
        itemsView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Creates the logout button
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
        longLabel.setText(location.getLongitude());

        TextView latLabel = findViewById(R.id.latLableId);
        latLabel.setText(location.getLatitude());

    }


    // Gets the locations from firebase and initializes the locations view
    private void getLocationItemsFromDB(String locationID) {
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childReference = mainDatabase.child("locations").child(locationID).child("Items");

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
                    double cost = 0;
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
                    itemsList.add(item);
                }
                // Initialize the locations view table
                initItemsView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    // Gets the locations from firebase and initializes the locations view
    private void getLocationsFromDB() {
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference childReference = mainDatabase.child("locations").child(locationID);

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterate through data from database

                    // Print database location info for debugging
                    /**
                     System.out.println("Name: " + x.child("Name").getValue());
                     System.out.println("Type: " + x.child("Type").getValue());
                     System.out.println("Longitude: " + x.child("Longitude").getValue());
                     System.out.println("Latitude: " + x.child("Latitude").getValue());
                     System.out.println("Street Address: " + x.child("Street Address").getValue());
                     System.out.println("City: " + x.child("City").getValue());
                     System.out.println("State: " + x.child("State").getValue());
                     System.out.println("Zip: " + x.child("Zip").getValue());
                     System.out.println("Phone: " + x.child("Phone").getValue());
                     **/

                    // Create a new location object from database data
                    location = new Location((String)dataSnapshot.child("Name").getValue(),
                            (String)dataSnapshot.child("Type").getValue(),
                            String.valueOf(dataSnapshot.child("Longitude").getValue()),
                            String.valueOf(dataSnapshot.child("Latitude").getValue()),
                            (String)dataSnapshot.child("Street Address").getValue(),
                            (String)dataSnapshot.child("City").getValue(),
                            (String)dataSnapshot.child("State").getValue(),
                            String.valueOf(dataSnapshot.child("Zip").getValue()),
                            (String)dataSnapshot.child("Phone").getValue(),
                            dataSnapshot.getKey());

                initLocationDetails();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
}