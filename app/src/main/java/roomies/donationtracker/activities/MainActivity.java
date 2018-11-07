package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roomies.donationtracker.adapters.LocationsViewAdapter;
import roomies.donationtracker.models.Location;


public class MainActivity extends AppCompatActivity {

    // Map to use for creating location list
    ArrayList<Location> locationsList = new ArrayList<>();
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLogoutButton();
        getLocationsFromDB();
        Intent myIntent = getIntent(); // gets the previously created intent
        String type = myIntent.getStringExtra("userType");
        userType = type;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocationsFromDB();
    }

    // Creates the locations view
    private void initLocationsView() {
        RecyclerView locationsView = findViewById(R.id.recyclerViewID);
        LocationsViewAdapter locationsViewAdapter = new LocationsViewAdapter(this, locationsList);
        locationsView.setAdapter(locationsViewAdapter);
        locationsView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Creates the logout button
    private void initLogoutButton() {
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    // Gets the locations from firebase and initializes the locations view
    private void getLocationsFromDB() {
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childReference = mainDatabase.child("locations");

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationsList = new ArrayList<>();
                // Iterate through data from database
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    // Create a new location object from database data
                    Location location = new Location((String)x.child("Name").getValue(),
                            (String)x.child("Type").getValue(),
                            String.valueOf(x.child("Longitude").getValue()),
                            String.valueOf(x.child("Latitude").getValue()),
                            (String)x.child("Street Address").getValue(),
                            (String)x.child("City").getValue(),
                            (String)x.child("State").getValue(),
                            String.valueOf(x.child("Zip").getValue()),
                            (String)x.child("Phone").getValue(),
                            x.getKey());

                    // Add new location to location list
                    locationsList.add(location);
                }

                // Initialize the locations view table
                initLocationsView();
                if(userType != null && userType.equals("location")) {
                    Location obj = locationsList.get(0); // remember first item
                    locationsList.clear(); // clear complete list
                    locationsList.add(obj); // add first item
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
}