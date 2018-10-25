package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roomies.donationtracker.R;
import roomies.donationtracker.adapters.ItemsViewAdapter;
import roomies.donationtracker.models.Item;


public class LocationActivity extends AppCompatActivity {

    // Map to use for creating location list
    ArrayList<Item> itemsList = new ArrayList<>();
    String locationID = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initDoneButton();
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
            getLocationItemsFromDB(locationID);
        }
    }

    // Creates the logout button
    private void initDoneButton() {
        Button doneButton = findViewById(R.id.doneButton_ID);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    // Creates the logout button
    private void initAddItemButton() {
        Button doneButton = findViewById(R.id.addItemButtonID);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationActivity.this, ItemActivity.class);
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


    // Gets the locations from firebase and initializes the locations view
    private void getLocationItemsFromDB(String locationID) {
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childReference = mainDatabase.child("locations").child(locationID).child("items");

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterate through data from database
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    // Create a new location object from database data
                    Item item = new Item((String) x.child("name").getValue(),
                            (String) x.child("type").getValue(),
                            (Float) x.child("cost").getValue(),
                            (String) x.child("donationDate").getValue(),
                            (String) x.child("donationLocation").getValue());

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
}