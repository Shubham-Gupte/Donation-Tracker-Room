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

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    // Views
    Button loginButton;
    Button registerButton;
    EditText userInput;
    EditText passwordInput;

    // Variables
    int loginAttemptsRemaining = 3;
    FirebaseAuth auth;

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

}
