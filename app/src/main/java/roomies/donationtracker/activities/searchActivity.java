package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import roomies.donationtracker.adapters.ItemsViewAdapter;
import roomies.donationtracker.models.Item;

public class searchActivity extends AppCompatActivity {
    //views
    EditText itemName;
    EditText itemType;
    String locationID = null;
    ArrayList<Item> itemsList = null;
    ArrayList<Item> firebaseList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);

        itemName = findViewById(R.id.itemName2);
        itemType = findViewById(R.id.type);

        initCancelButton();
        //initSearchButton();
        getIncomingIntent();
        itemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameFilter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void nameFilter(String s) {
        ArrayList<Item> filteredList = new ArrayList<>();

        for (Item item : itemsList) {
            if (!item.getName().toLowerCase().contains(s.toLowerCase())) {
                itemsList.remove(item);
            }
        }
        initItemsView();
    }



    private void initItemsView(){
        RecyclerView itemsView = findViewById(R.id.recyclerViewID);
        ItemsViewAdapter itemsViewAdapter = new ItemsViewAdapter(itemsList);
        itemsView.setAdapter(itemsViewAdapter);
        itemsView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCancelButton() {
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(searchActivity.this, LocationDetailsActivity.class);
                i.putExtra("location_ID", locationID);
                startActivity(i);
            }
        });
    }

    // Gets the location ID sent with the intent
    private void getIncomingIntent() {
        if (getIntent().hasExtra("location_ID")) {
            locationID = getIntent().getStringExtra("location_ID");
        }
        if (getIntent().hasExtra("itemslist")) {
            itemsList = (ArrayList<Item>) getIntent().getSerializableExtra("itemslist");
        }
    }


    private void setList(ArrayList<Item> in) {
        this.firebaseList = in;
    }

    private ArrayList<Item> getList() {
        return this.firebaseList;
    }

}
