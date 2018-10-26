package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import roomies.donationtracker.adapters.LocationsViewAdapter;

public class SearchActivity {
    List<String> spinnerArray = Arrays.asList("Search By Category", "Search By Item Name");
    List<String> locationsList = new ArrayList<>();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
//        initSpinner();
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }

    //populate spinner with search by category and search by item
//    private void initSpinner() {
//        Spinner spinner = findViewById(R.id.searchSpinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }



}
