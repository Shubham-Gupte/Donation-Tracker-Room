package roomies.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button register;
    EditText user;
    EditText password;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registrationButton);
        user = (EditText) findViewById(R.id.userInput);
        password = (EditText) findViewById(R.id.passwordInput);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (user.getText().toString().equals("user")
                        && password.getText().toString().equals("pass")) {
                    //correct, login
                    Toast.makeText(getApplicationContext(),
                            "Correct! Logging you in...", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    //incorrect
                    Toast.makeText(getApplicationContext(),
                            "Wrong login credentials. Try again.", Toast.LENGTH_SHORT).show();
                    counter--;
                    if (counter == 0) {
                        //after 3 attempts disable login button
                        login.setEnabled(false);
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }

}
