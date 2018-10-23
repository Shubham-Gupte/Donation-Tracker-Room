package roomies.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class ItemActivity extends AppCompatActivity {

    //views
    EditText itemName;
    EditText itemCost;
    EditText itemType;
    EditText donationLocation;
    EditText donationDate;
    String locationID = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        initCancelButton();
        initAddButton();
        getIncomingIntent();
    }

    private void initCancelButton() {
        Button cancel = findViewById(R.id.cancelRegistrationButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemActivity.this, LocationActivity.class);
                startActivity(i);
            }
        });
    }

    private void initAddButton() {
        Button addButton = findViewById(R.id.cancelButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ItemActivity.this, LocationActivity.class);
                intent.putExtra("location_ID", locationID);
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
    private void addItemToDatabase(Item item) {
        //creating the item
    }
}
