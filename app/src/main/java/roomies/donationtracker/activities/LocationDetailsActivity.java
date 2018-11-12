package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roomies.donationtracker.models.Item;
import roomies.donationtracker.models.Location;

/**
 * When a location is tapped, shows viewer the details for that location
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 */
public class LocationDetailsActivity extends AppCompatActivity {

    /**
     * location that is selected
     */
    ArrayList<Item> itemsList = new ArrayList<>();
    Location location = null;
    String locationID = null;


    /**
     * initializes view the first time activity is opened
     *
     * @param savedInstanceState saved instance state
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_details);
        initDoneButton();
        initViewItemsButton();
        getIncomingIntent();
    }

    /**
     * initializes view every time the view is opened
     * @return void
     */
    @Override
    protected void onStart() {
        super.onStart();
        getIncomingIntent();
    }

    /**
     * gets locaction ID sent as an extra with intent
     * @return void
     */
    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
            getLocationsFromDB();
        }
    }

    /**
     * initializes done button on screen, which directs viewer back to the previous screen
     * @return void
     */
    private void initDoneButton() {
        Button doneButton = findViewById(R.id.doneButton_ID);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * initializes view items button, which shows item list for that location
     * @return void
     */
    private void initViewItemsButton() {
        Button doneButton = findViewById(R.id.viewItemButtonID
        );
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationDetailsActivity.this, ItemsActivity.class);
                i.putExtra("location_ID", locationID);
                startActivity(i);
            }
        });
    }

    /**
     * initializes location details, displays all as text on screen
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
     * Gets the locations from firebase and initializes the location details
     * @return void
     */
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
                            (double) dataSnapshot.child("Longitude").getValue(),
                            (double) dataSnapshot.child("Latitude").getValue(),
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