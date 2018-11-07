package roomies.donationtracker.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
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


/**
 * The class that launches the main screen when the android app opens
 *
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     *     Map to use for creating location list
     */
    ArrayList<Location> locationsList = new ArrayList<>();
    /**
     * type of user entering MainActivity
     */
    String userType;


    /**
     * actions to be carried out on the first instantiation of the app
     *
     * @param savedInstanceState the saved instance state
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize tabbed view
//        initActionBar();


        //initialize buttons
        initLogoutButton();
        initItemButton();
        initMapButton();
        getLocationsFromDB();

        //gets usertype
        Intent myIntent = getIntent(); // gets the previously created intent
        String type = myIntent.getStringExtra("userType");
        userType = type;
    }


    /**
     * on the opening of the app, fetche locations from firebase and shows UI
     *
     * @return void
     */
    @Override
    protected void onStart() {
        super.onStart();
        getLocationsFromDB();
    }

    /**
     * create button with text "view all items" that takes the user to the ItemsActivity class
     * @return void
     */
    private void initItemButton() {
        Button itemButton = findViewById(R.id.itemButton);
        itemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ItemsActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * create map button that takes viewer to the screen that shows map with all locations
     *
     * @return void
     */
    private void initMapButton() {
        Button mapButton = findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }

//    //create action bar with multiple fragments
//    private void initActionBar() {
//        final ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);
//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            @Override
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//                //when you select the tab, show the appropriate fragment
//            }
//
//            @Override
//            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//                //when you unselect the tab
//            }
//
//            @Override
//            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//            }
//        };
//
//        actionBar.addTab(actionBar.newTab().setText("List").setTabListener(tabListener));
//        actionBar.addTab(actionBar.newTab().setText("Map").setTabListener(tabListener));
//    }


    /**
     * Creates the locations recycler view that is displayed on the home screen of the app
     *
     * @return void
     */
    private void initLocationsView() {
        RecyclerView locationsView = findViewById(R.id.recyclerViewID);
        LocationsViewAdapter locationsViewAdapter = new LocationsViewAdapter(this, locationsList);
        locationsView.setAdapter(locationsViewAdapter);
        locationsView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     *     Creates the logout button, which takes user back to login screen and restarts app
     *     @return void
     */
    private void initLogoutButton() {
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Gets the locations from firebase and initializes the locations view
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