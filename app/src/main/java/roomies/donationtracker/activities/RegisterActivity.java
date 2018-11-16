package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

/**
 * class that allows someone to register a suer
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class RegisterActivity extends AppCompatActivity {

    // Views
    Button registerButton;
    Button cancelRegistrationButton;
    EditText user;
    EditText password;
    EditText confirmPassword;
    Switch adminSwitch;
    EditText adminKey;
    Spinner userType;
    List<String> spinnerArray = Arrays.asList("Intake Employee", "Warehouse Employee", "Intake Employee", "Manager", "Cashier", "Admin");

    // Variables
    String key = "testKey123";
    private FirebaseAuth auth;
    private DatabaseReference mainDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();

        // Initialize views
        initUserType();
        initRegisterButton();
        initAdminSwitch();
        initcancelRegistrationButton();

    }
    /**
     * init user type spinner
     * @return void
     */
    private void initUserType(){
        userType = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);
    };
    /**
     * initialize cancel button, which takes user back to login screen
     * @return void
     */
    private void initcancelRegistrationButton() {
        cancelRegistrationButton = findViewById(R.id.cancelRegistrationButton);
        cancelRegistrationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * Method that adds user to firebase database
     * @param username the username of user
     * @param password the password of user being added
     * @param userType the type of user
     */
    private void firebaseRegister(final String username, String password, final String userType) {
        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    //add it to the database with the user id and user type
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
//        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference usersReference = mainDatabase.child("users").child(userUID);
//        usersReference.push().setValue(userType);
    }
    /**
     * initialize admin switch, which authenticates whether or not the user is actually an admin
     * @return void
     */
    private void initAdminSwitch() {
        adminSwitch = findViewById(R.id.adminSwitch);
        adminKey = findViewById(R.id.adminAuthentication);
        adminSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminKey.getVisibility() == View.GONE) {
                    adminKey.setVisibility(View.VISIBLE);
                    findViewById(R.id.adminText).setVisibility(View.VISIBLE);
                } else {
                    adminKey.setVisibility(View.GONE);
                    findViewById(R.id.adminText).setVisibility(View.GONE);
                }
            }
        });
    }
    /**
     * initialize register button, which checks if passwords match and adds user to firebase
     * @return void
     */
    private void initRegisterButton() {
        registerButton = findViewById(R.id.signUpButton);
        //for right now, the registerButton button will just check if the passwords match
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = findViewById(R.id.editID);
                password = findViewById(R.id.editPassword);
                confirmPassword = findViewById(R.id.editPassword2);
                adminKey = findViewById(R.id.adminAuthentication);
                userType = findViewById(R.id.spinner);
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    String userTypeString = userType.getSelectedItem().toString();
                    if (userTypeString.equals("Admin")) {
                        // verify admin key
                        if (adminKey.getText().toString().equals(key)) {
                            firebaseRegister(user.getText().toString(), password.getText().toString(), userTypeString);
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                            // register admin
                        } else {
                            //deny
                            AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                            fail.setTitle("Registration");
                            fail.setMessage("Admin Password is wrong!");
                            fail.show();
                        }

                    } else {
                        // just register user
                        firebaseRegister(user.getText().toString(), password.getText().toString(), userTypeString);
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                } else {
                    AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                    fail.setTitle("Registration");
                    fail.setMessage("Passwords do not Match!");
                    fail.show();
                }
            }
        });
    }
}