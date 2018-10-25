package roomies.donationtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    //views
    EditText itemName;
    EditText itemCost;
    EditText itemType;
    EditText donationLocation;
    EditText donationDate;
    String locationID = null;
    ArrayList<Item> firebaseList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        itemName = findViewById(R.id.itemCost2);
        itemCost = findViewById(R.id.itemCost);
        itemType = findViewById(R.id.type);
        donationDate = findViewById(R.id.itemName);
        donationLocation = findViewById(R.id.itemType);

        initCancelButton();
        initAddButton();
        getIncomingIntent();
    }

    private void initCancelButton() {
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemActivity.this, LocationActivity.class);
                startActivity(i);
            }
        });
    }

    private void initAddButton() {
        Button addButton = findViewById(R.id.addItemButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemActivity.this, LocationActivity.class);

                Item toAdd = new Item(itemName.getText().toString(), itemType.getText().toString(),
                        Float.parseFloat(itemCost.getText().toString()), donationDate.getText().toString(),
                        donationLocation.getText().toString());
                addItemToDatabase(toAdd);
                startActivity(i);
            }
        });
    }

    // Gets the location ID sent with the intent
    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
        }
    }

    //add item to firebase
    private void addItemToDatabase(final Item item) {
        locationID = item.getDonationLocation();

        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationsReference = mainDatabase.child("locations");
        DatabaseReference childReference = locationsReference.child(locationID).child("itemList");
        childReference.push().setValue(item);
        //use the push or put command
    }

    private void setList(ArrayList<Item> in) {
        this.firebaseList = in;
    }

    private ArrayList<Item> getList() {
        return this.firebaseList;
    }
}
