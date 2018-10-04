package roomies.donationtracker;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {
    Button registerButton;
    Button cancelRegistrationButton;
    EditText user;
    EditText password;
    EditText confirmPassword;
    Switch adminSwitch;
    EditText adminKey;
    String key = "testKey123";
    static HashMap userList;

    public static HashMap<String, String> getUserList() {
        return userList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        adminKey = findViewById(R.id.adminAuthentication);
        registerButton = findViewById(R.id.signUpButton);
        userList = LoginActivity.getUserList();

        //for right now, the register button will just check if the passwords match
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = findViewById(R.id.editPassword);
                confirmPassword = findViewById(R.id.editPassword2);
                user = findViewById(R.id.editID);
                if (adminKey.getVisibility() == View.GONE) {
                    if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                        userList.put(user.getText().toString(), password.getText().toString());
                        LoginActivity.setUserList(userList);
                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                        fail.setTitle("Registration");
                        fail.setMessage("Passwords do not Match!");
                        fail.show();
                    }
                } else {
                    if (password.getText().toString().equals(confirmPassword.getText().toString()) &&
                           adminKey.getText().toString().equals(key)) {
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

        cancelRegistrationButton = findViewById(R.id.cancelRegistrationButton);
        cancelRegistrationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        adminSwitch = findViewById(R.id.adminSwitch);
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
}