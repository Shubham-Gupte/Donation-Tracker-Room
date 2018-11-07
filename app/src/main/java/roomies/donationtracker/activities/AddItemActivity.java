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


/**
 * class that allows an intake employee to add an item at the location where they are employed
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class AddItemActivity extends AppCompatActivity {

    /**
     * the attributes of every added item transaction
     */
    EditText itemName;
    EditText itemCost;
    EditText itemType;
    EditText donationDate;
    String locationID = null;
    /**
     * list of items attributed to this location, kept in firebase
     */
    ArrayList<Item> firebaseList = null;

    /**
     * creates the view for the first time by initializing buttons etc.
     *
     * @param savedInstanceState
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
     * initialize Cancel button, which takes viewer back to ItemsActivity page
     * @return void
     */
    private void initCancelButton() {
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * init add button, which confirms the user input and takes user back to ItemsActivity and
     * adds item to firebase list
     *
     * @return void
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
     *     Gets the location ID of the current employee, which is sent with the intent
     *     @return void

     */
    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
        }
    }

    /**
     * add item to firebase
     * @param item item added in this transaction
     */
    private void addItemToDatabase(final Item item) {
        DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationsReference = mainDatabase.child("locations").child(locationID).child("Items");
        locationsReference.push().setValue(item);
    }

    /**
     * update the item list in firebase with new item
     * @param in the list with the added item
     */
    private void setList(ArrayList<Item> in) {
        this.firebaseList = in;
    }

    /**
     * pull item list for this location from firebase
     *
     * @return current item list for this location.
     */
    private ArrayList<Item> getList() {
        return this.firebaseList;
    }
}
