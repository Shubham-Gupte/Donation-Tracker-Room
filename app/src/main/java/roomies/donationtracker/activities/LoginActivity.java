package roomies.donationtracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
/**
 * class that allows users to login
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Creates the variables for onscreen elements
     */
    Button loginButton;
    Button registerButton;
    EditText userInput;
    EditText passwordInput;

    // Variables
    int loginAttemptsRemaining = 3;
    FirebaseAuth auth;

    /**
     * Method that runs when view is loaded
     * @param savedInstanceState the previous instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        // User list setup

        // Initialize views
        initUsernameInput();
        initPasswordInput();
        initLoginButton();
        initRegisterButton();
    }

    /**
     * Initializes the log in button on screen
     */
    private void initLoginButton() {
        loginButton = findViewById(R.id.loginButton);

        // Login button functionality
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = userInput.getText().toString();
                String password = passwordInput.getText().toString();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Correct! Logging you in...", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
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
        });
    }

    /**
     * Initializes the register button on screen
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
     * Initializes the username input on screen
     */
    private void initUsernameInput() {
        userInput = findViewById(R.id.userInput);
    }

    /**
     * Initializes the password input on screen
     */
    private void initPasswordInput() {
        passwordInput = findViewById(R.id.passwordInput);
    }

}