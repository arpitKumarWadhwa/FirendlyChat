package com.example.arpit.nurture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    MyHelper helper = new MyHelper(this);
    String email,pass;
    EditText e_email,e_pass;
    Button login;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        e_email = findViewById(R.id.editText);
        e_pass = findViewById(R.id.editText2);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = e_email.getText().toString();
                pass = e_pass.getText().toString();

                User temp = helper.readUser(email);

                if(temp == null){
                    Toast.makeText(UserActivity.this, "Account not found! Please register.", Toast.LENGTH_LONG).show();
                }
                else if(temp.getPass().equals(pass)){
                    sp = PreferenceManager.getDefaultSharedPreferences(UserActivity.this);
                    SharedPreferences.Editor edit = sp.edit();

                    edit.putString("email", temp.getEmail());
                    edit.putString("name", temp.getName());
                    edit.commit();

                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(UserActivity.this, "Incorrect Password!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}