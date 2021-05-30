package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {
    static final String TAG = "MainActivity";
    EditText username;
    EditText password;
    TextView message;
    Button btn_signin;
    Button btn_signup;
    List<Person> persons;
    Integer attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineVariables();
        defineListeners();
    }

    private void defineVariables(){
        attempt = 0;
        persons = Person.getPersonList();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        message = (TextView) findViewById(R.id.message);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signup = (Button) findViewById(R.id.btn_signup);

    }

    private void defineListeners(){

        btn_signin.setOnClickListener(v -> {
            Intent intent;
            if(checkPerson()){
                intent = new Intent(v.getContext(),MenuActivity.class);
                intent.putExtra("username",username.getText().toString());
                cleanTextBoxes();
                startActivity(intent);
            }
            else{
                cleanTextBoxes();
                attempt += 1;
                Toast.makeText(MainActivity.this,"Username or password is wrong!",Toast.LENGTH_SHORT).show();
                if(attempt >= 3){
                    Toast.makeText(MainActivity.this,"You logged in incorrectly 3 times!",Toast.LENGTH_SHORT).show();
                    btn_signin.setEnabled(false);
                    finish();
                }
            }
        });
        btn_signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkPerson(){
        for(Person aPerson : persons){
            if(username.getText().toString().equals(aPerson.getUserName()) &&
                    password.getText().toString().equals(aPerson.getPassword())){
                return true;
            }

        }
        return false;
    }

    private void cleanTextBoxes(){
        username.setText("");
        password.setText("");
    }
}