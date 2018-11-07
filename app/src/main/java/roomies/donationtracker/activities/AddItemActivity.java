package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import roomies.donationtracker.models.Item;

public class AddItemActivity extends AppCompatActivity {

    //views
    EditText itemName;
    EditText itemCost;
    EditText itemType;
    EditText donationDate;
    String locationID = null;
    ArrayList<Item> firebaseList = null;

    /**
     * This is the onCreate method for our Add Item Activity, runs on the first instance
     * @param savedInstanceState updates every time an intent is acted upon
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemName = findViewById(R.id.itemCost2);
        itemCost = findViewById(R.id.itemCost);
        itemType = findViewById(R.id.type);
        donationDate = findViewById(R.id.itemName);

        initCancelButton();
        initAddButton();
        getIncomingIntent();
    }

    /**
     * Initializes Cancel button fin the view, also assigns its click function
     */
    private void initCancelButton() {
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddItemActivity.this, LocationDetailsActivity.class);
                i.putExtra("location_ID", locationID);
                startActivity(i);
            }
        });
    }
    /**
     * Initializes Add button fin the view, also assigns its click function
     */
    private void initAddButton() {
        Button addButton = findViewById(R.id.addItemButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Item toAdd = new Item(itemName.getText().toString(), itemType.getText().toString(),
                        Float.valueOf(itemCost.getText().toString()), donationDate.getText().toString(),
                        locationID);
                addItemToDatabase(toAdd);
                Intent i = new Intent(AddItemActivity.this, LocationDetailsActivity.class);
                i.putExtra("location_ID", locationID);
                startActivity(i);
            }
        });
    }

    /**
     * Gets the location ID sent with the intent
     */
    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
        }
    }

    /**
     * Method to add item to firebase
     * @param item The method to add
     */
    private void addItemToDatabase(final Item item) {
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationsReference = mainDatabase.child("locations").child(locationID).child("Items");
        locationsReference.push().setValue(item);
    }

    /**
     * Sets the list of items to list
     * @param in the list of items
     */
    private void setList(ArrayList<Item> in) {
        this.firebaseList = in;
    }

    /**
     * Gets the list of items
     * @return list of items
     */
    private ArrayList<Item> getList() {
        return this.firebaseList;
    }
}
