package roomies.donationtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    Button logout;
    TextView locationText;

    DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childReference = mainDatabase.child("locations");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);

        locationText = findViewById(R.id.textViewLocation);
        logout = findViewById(R.id.logoutButton);

//        DataSnapshot snap = mainDatabase.child("locations");
//        for (DataSnapshot x : snap.getChildren()) {
//            System.out.println(x.child("Name").getValue());
//            System.out.println(x.child("Type").getValue());
//            System.out.println(x.child("City").getValue());
//        }

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long text = dataSnapshot.getChildrenCount();
                String l = String.valueOf(text);
                locationText.setText(l);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
