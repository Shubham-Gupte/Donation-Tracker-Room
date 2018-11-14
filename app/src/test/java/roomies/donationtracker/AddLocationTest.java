package roomies.donationtracker;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import roomies.donationtracker.models.Database;
import roomies.donationtracker.models.Location;
import roomies.donationtracker.models.User;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

/**
 * Test that focuses on adding locations to the firebase database,
 * which will execute on the development machine (host).
 *
 * @author Polly Ouellette
 * @version 1.0
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AddLocationTest {

    Database db = new Database();
    ArrayList<Location> locationList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();
//    FirebaseApp.initializeApp(this);
    DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference locationReference = mainDatabase.child("locations");

    @Test
    public void addsLocation() {
        Location loc = new Location("Polly's place");
        locationReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean contains = false;
                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    if (x.child("Name").equals("Polly's place")) {
                        contains = true;
                    }
                }
                assertTrue(contains);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        db.addLocation(loc);

    }
}