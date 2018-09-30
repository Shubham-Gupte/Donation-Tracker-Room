package roomies.donationtracker;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    Button registerButton;
    Button cancelRegistrationButton;
    EditText user;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        registerButton = findViewById(R.id.signUpButton);

        //for right now, the register button will just check if the passwords match
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = findViewById(R.id.editPassword);
                confirmPassword = findViewById(R.id.editPassword2);
                user = findViewById(R.id.editName);
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    AlertDialog fail = new AlertDialog.Builder(RegisterActivity.this).create();
                    fail.setTitle("Registration");
                    fail.setMessage("Passwords do not Match!");
                    fail.show();
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
    }
}