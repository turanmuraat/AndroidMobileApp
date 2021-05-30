package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    static final String TAG = "SignUpActivity";
    EditText name;
    EditText surname;
    EditText username;
    EditText email;
    EditText phoneNumber;
    EditText birthDate;
    EditText password;
    EditText rePassword;
    Button btn_signup;
    TextView message;
    List<Person> persons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        defineVariables();
        defineListeners();
    }

    private void defineVariables() {
        persons = Person.getPersonList();
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        birthDate = (EditText) findViewById(R.id.birthDate);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.rePassword);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        message = (TextView) findViewById(R.id.message);
    }

    private void defineListeners() {
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(checkFields()){
                    Person person = new Person(username.getText().toString(),password.getText().toString(),
                            name.getText().toString(),surname.getText().toString(),birthDate.getText().toString(),
                            email.getText().toString(),phoneNumber.getText().toString());
                    Person.addDataToFirebase(person);
                    intent = new Intent(v.getContext(), MainActivity.class);
                    intent.putExtra("Person",person);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkFields(){
        for (Person person:persons) {
            if(person.getUserName() == username.getText().toString() || person.getEmail() == email.getText().toString()){
                Toast.makeText(SignupActivity.this,"There is already user!",Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        if(!password.getText().toString().equals(rePassword.getText().toString())){
            password.setText("");
            rePassword.setText("");
            Toast.makeText(SignupActivity.this,"Passwords must be same!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
        phoneNumber.getText().toString().isEmpty() || birthDate.getText().toString().isEmpty() || password.getText().toString().isEmpty()
        || rePassword.getText().toString().isEmpty()){
            Toast.makeText(SignupActivity.this,"All fields must be field!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}