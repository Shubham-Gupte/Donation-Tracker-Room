package roomies.donationtracker.models;

import android.support.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
//    ArrayList<User> userList = new ArrayList<>();
//    DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
//    DatabaseReference locationReference = mainDatabase.child("locations");

    /**
     * instantiates a database
     * @return void
     */
    public Database() {}

    /**
     * retrieves users from firebase
     * @return ArrayList<User></User>
     */
    public ArrayList<User> getUsersFromFirebase() {
        return null;
    }

    /**
     * add location to firebase and to locationsList
     *
     * @param location location to be added
     * @return boolean whether location was added
     */
    public boolean addLocation(Location location) {
        boolean added = false;
        if (location == null) {
            return added;
        }
        String name = location.getLocationName();
        for (Location x : locationList) {
            if (x.getLocationName().equals(name)) {
                //if the list already contains location, return false and dont add
                return added;
            }
        }
        locationList.add(location);
        added = true;
        return added;
    }

    /**
     * removes location from firebase adn from locationsList
     * @param location location to be removed
     * @return new list without location
     */
    public ArrayList<Location> removeLocation(Location location){
        return null;
    }

    /**
     * get locationList
     * @return locationList
     */
    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    /**
     * set locationList
     * @param locationList to be set
     */
    public void setLocationList(ArrayList<Location> locationList) {
        this.locationList = locationList;
    }
}
