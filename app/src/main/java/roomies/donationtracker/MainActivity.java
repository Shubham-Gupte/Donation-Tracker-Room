package roomies.donationtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    Button logout;
    TextView locationText;


    DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childReference = mainDatabase.child("locations");
    Map<String, Location> allLocations = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);

        locationText = findViewById(R.id.textViewLocation);
        logout = findViewById(R.id.logoutButton);


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
                String l = String.valueOf(text);
                locationText.setText("");
                for (DataSnapshot x : dataSnapshot.getChildren()) {
             /**    System.out.println("Name: " + x.child("Name").getValue());
                    System.out.println("Type: " + x.child("Type").getValue());
                    System.out.println("Longitude: " + x.child("Longitude").getValue());
                    System.out.println("Latitude: " + x.child("Latitude").getValue());
                    System.out.println("Street Address: " + x.child("Street Address").getValue());
                    System.out.println("City: " + x.child("City").getValue());
                    System.out.println("State: " + x.child("State").getValue());
                    System.out.println("Zip: " + x.child("Zip").getValue());
                    System.out.println("Phone: " + x.child("Phone").getValue()); **/
                    Location toAdd = new Location((String)x.child("Name").getValue(),
                            (String)x.child("Type").getValue(),
                            String.valueOf(x.child("Longitude").getValue()),
                            String.valueOf(x.child("Latitude").getValue()),
                            (String)x.child("Street Address").getValue(),
                            (String)x.child("City").getValue(),
                            (String)x.child("State").getValue(),
                            String.valueOf(x.child("Zip").getValue()),
                            (String)x.child("Phone").getValue());
                    allLocations.put(toAdd.getLocationName(), toAdd);

                }
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
