package roomies.donationtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    // Views
    Button logout;
    TextView locationText;

    // Firebase connection reference
    DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childReference = mainDatabase.child("locations");

    // Map to use for creating location list
    Map<String, Location> allLocations = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);

        // Assign view id's
        locationText = findViewById(R.id.textViewLocation);
        logout = findViewById(R.id.logoutButton);

        // Logout button functionality
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long text = dataSnapshot.getChildrenCount();
                locationText.setText("");

                // Iterate through data from database
                for (DataSnapshot x : dataSnapshot.getChildren()) {

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
                    Location toAdd = new Location((String)x.child("Name").getValue(),
                            (String)x.child("Type").getValue(),
                            String.valueOf(x.child("Longitude").getValue()),
                            String.valueOf(x.child("Latitude").getValue()),
                            (String)x.child("Street Address").getValue(),
                            (String)x.child("City").getValue(),
                            (String)x.child("State").getValue(),
                            String.valueOf(x.child("Zip").getValue()),
                            (String)x.child("Phone").getValue());

                    // Add new location to location list
                    allLocations.put(toAdd.getLocationName(), toAdd);
                }

                // Location dividers
                for (Location place: allLocations.values()) {
                    locationText.append(place.toString() + "\n______\n");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                }
        });
    }
}