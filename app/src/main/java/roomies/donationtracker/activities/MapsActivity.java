package roomies.donationtracker.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import roomies.donationtracker.models.Location;

/**
 * displays map with a marker at each location
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuuf
 * @version 1.0
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /**
     * map from Google maps api
     */
    private GoogleMap mMap;
    /**
     * locations list from firebase
     */
    ArrayList<Location> locationsList = new ArrayList<>();

    /**
     * initializes app view  the first time it is created
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocationsFromDB();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * @param googleMap the map from Google API
     * @return void
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(33.78, -84.4);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Atlanta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    /**
     * get locations from firebase
     *
     * @return void
     */
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
                    Location location = new Location((String)x.child("Name").getValue(),
                            (String)x.child("Type").getValue(),
                            (double) x.child("Longitude").getValue(),
                            (double) x.child("Latitude").getValue(),
                            (String)x.child("Street Address").getValue(),
                            (String)x.child("City").getValue(),
                            (String)x.child("State").getValue(),
                            String.valueOf(x.child("Zip").getValue()),
                            (String)x.child("Phone").getValue(),
                            x.getKey());
                    // Add new location to location list
                    locationsList.add(location);
                }
                addLocations(locationsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    /**
     * add a marker for all locations
     * @param locList arraylist of locations
     */
    private void addLocations(ArrayList<Location> locList) {
        for (Location x: locList) {
            //create lat lang object
            LatLng latAndLang = new LatLng(x.getLatitude(), x.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latAndLang).title(x.getLocationName()));
        }
    }
}
