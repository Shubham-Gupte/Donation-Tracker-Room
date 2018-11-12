package roomies.donationtracker.models;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A class that represents a database that stores all locations, kept in firebase
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class Database {

    /**
     * variables
     */
    ArrayList<Location> locationList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();

    /**
     * instantiates a database
     * @return void
     */
    public Database() {}

    /**
     * retrieves locations from Firebase
     * @return ArrayList<Location></Location>
     */
    public ArrayList<Location> getLocationsFromFirebase(){
        // Firebase connection reference
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childReference = mainDatabase.child("locations");

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locationList = new ArrayList<>();
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
                    locationList.add(location);
                }
//                // Initialize the locations view table
//                initLocationsView();
//                if(userType != null && userType.equals("location")) {
//                    Location obj = locationsList.get(0); // remember first item
//                    locationsList.clear(); // clear complete list
//                    locationsList.add(obj); // add first item
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return locationList;

    }

    /**
     * retrieves users from firebase
     * @return ArrayList<User></User>
     */
    public ArrayList<User> getUsersFromFirebase() {
        return userList;
    }

    /**
     * add location to firebase and to locationsList
     *
     * @param location location to be added
     */
    public void addLocation(Location location) {

    }

    /**
     * removes location from firebase adn from locationsList
     * @param location location to be removed
     * @return new list without location
     */
    public ArrayList<Location> removeLocation(Location location){

    }


}
