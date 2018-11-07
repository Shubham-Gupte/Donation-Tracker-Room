package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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
    List<String> spinnerArray = Arrays.asList("Intake Employee", "Warehouse Employee", "Intake Employee", "Manager", "Cashier");

    // Variables
    String key = "testKey123";
    static HashMap userList;

    public static HashMap<String, String> getUserList() {
        return userList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userList = roomies.donationtracker.activities.LoginActivity.getUserList();

        // Initialize views
        initUserType();
        initRegisterButton();
        initAdminSwitch();
        initcancelRegistrationButton();

    }

    private void initUserType(){
        userType = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType.setAdapter(adapter);
    };

    private void initcancelRegistrationButton() {
        cancelRegistrationButton = findViewById(R.id.cancelRegistrationButton);
        cancelRegistrationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


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
//                userType = (Spinner) findViewbyId(R.id.spinner);


                if (adminKey.getVisibility() == View.GONE) {
                    if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                        userList.put(user.getText().toString(), password.getText().toString());
                        LoginActivity.setUserList(userList);
                        finish();
                    } else {
                        AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                        fail.setTitle("Registration");
                        fail.setMessage("Passwords do not Match!");
                        fail.show();
                    }
                } else {
                    if (password.getText().toString().equals(confirmPassword.getText().toString()) &&
                            adminKey.getText().toString().equals(key)) {
                        userList.put(user.getText().toString(), password.getText().toString());
                        LoginActivity.setUserList(userList);

                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);

                        startActivity(i);
                    } else if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                        AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                        fail.setTitle("Registration");
                        fail.setMessage("Admin Authentication incorrect!");
                        fail.show();
                    } else {
                        AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                        fail.setTitle("Registration");
                        fail.setMessage("Passwords do not Match!");
                        fail.show();
                    }
                }
            }
        });
    }
}