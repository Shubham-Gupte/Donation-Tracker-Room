package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * view that allows user to login
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * attributes shown on the screen like buttons and edit text fields
     */
    Button loginButton;
    Button registerButton;
    EditText userInput;
    EditText passwordInput;

    /**
     * user list used to check if input is a valid user
     */
    static HashMap userList;

    /**
     * login attempts remaining
     */
    int loginAttemptsRemaining = 3;

    /**
     * initializes view the first time the app is created
     * @param savedInstanceState saved instance state
     */
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

    /**
     * initializes login button, which checks if user has entered valid information if so, sends
     * them to the MainActivity screen
     * @return void
     */
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
                } else if(userInput.getText().toString().equals("cashier")) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("userType","cashier");
                    startActivity(i);
                } else if(userInput.getText().toString().equals("location")) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("userType","location");
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

    /**
     * init register button, which on a click takes user to RegisterActivity and create new user
     * @return void
     */
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

    /**
     * initialize username input text field
     * @return void
     */
    private void initUsernameInput() {
        userInput = findViewById(R.id.userInput);
    }

    /**
     * initialize password input text field, which hides characters
     * @return void
     */
    private void initPasswordInput() {
        passwordInput = findViewById(R.id.passwordInput);
    }

    /**
     * fetch user list from firebase
     * @return the user list, obtained from firebase
     */
    public static  HashMap<String, String> getUserList() {
        return userList;
    }

    /**
     * set updated user list
     * @param users HashMap with users
     */
    public static  void setUserList(HashMap<String, String> users) {
        userList = users;
    }

}
