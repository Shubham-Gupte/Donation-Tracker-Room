package roomies.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    // Views
    Button loginButton;
    Button registerButton;
    EditText userInput;
    EditText passwordInput;

    // Variables
    static HashMap userList;
    int loginAttemptsRemaining = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // User list setup
        userList = new HashMap<String, String>();
        if (userList.isEmpty()) {
            userList = RegisterActivity.getUserList();
        }
        if (userList == null) {
            userList = new HashMap<String, String>();
        }

        // Initialize views
        initUsernameInput();
        initPasswordInput();
        initLoginButton();
        initRegisterButton();
    }

    private void initLoginButton() {
        loginButton = findViewById(R.id.loginButton);

        // Login button functionality
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (userList.containsKey(userInput.getText().toString())
                        && userList.containsValue(passwordInput.getText().toString())) {
                    //correct, loginButton
                    Toast.makeText(getApplicationContext(),
                            "Correct! Logging you in...", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    //incorrect
                    Toast.makeText(getApplicationContext(),
                            "Wrong loginButton credentials. Try again.", Toast.LENGTH_SHORT).show();
                    loginAttemptsRemaining--;
                    if (loginAttemptsRemaining == 0) {
                        //after 3 attempts disable loginButton button
                        loginButton.setEnabled(false);
                    }
                }
            }
        });
    }

    private void initRegisterButton() {
        registerButton = findViewById(R.id.registrationButton);

        // Register button functionality
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                // Take to loginButton activity
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void initUsernameInput() {
        userInput = findViewById(R.id.userInput);
    }

    private void initPasswordInput() {
        passwordInput = findViewById(R.id.passwordInput);
    }

    public static  HashMap<String, String> getUserList() {
        return userList;
    }

    public static  void setUserList(HashMap<String, String> users) {
        userList = users;
    }

}
