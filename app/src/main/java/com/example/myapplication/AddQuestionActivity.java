package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuestionActivity extends AppCompatActivity {
    EditText question;
    EditText answer1;
    EditText answer2;
    EditText answer3;
    EditText answer4;
    EditText correctAnswer;
    EditText difficultyLevel;
    Button btn_add_question;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        defineVariables();
        defineListeners();
    }

    private void defineVariables() {
        question = (EditText) findViewById(R.id.question);
        answer1 = (EditText) findViewById(R.id.answer1);
        answer2 = (EditText) findViewById(R.id.answer2);
        answer3 = (EditText) findViewById(R.id.answer3);
        answer4 = (EditText) findViewById(R.id.answer4);
        correctAnswer = (EditText) findViewById(R.id.correctAnswer);
        difficultyLevel = (EditText) findViewById(R.id.difficultyLevel);
        btn_add_question = (Button) findViewById(R.id.btn_add_question);
        Intent intent = getIntent();
        currentUser = intent.getStringExtra("username");
    }
    private void defineListeners() {
        btn_add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(checkFields()){
                    Question questions = new Question(question.getText().toString(),answer1.getText().toString(),
                            answer2.getText().toString(),answer3.getText().toString(),answer4.getText().toString(),
                            correctAnswer.getText().toString(),difficultyLevel.getText().toString(), currentUser);
                    Question.addDataToFirebase(questions);
                    Toast.makeText(AddQuestionActivity.this,"Question is added!",Toast.LENGTH_SHORT).show();
                    intent = new Intent(v.getContext(), MenuActivity.class);
                    intent.putExtra("username", currentUser);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkFields(){
        if(question.getText().toString().isEmpty() && answer1.getText().toString().isEmpty() && answer2.getText().toString().isEmpty() &&
                answer3.getText().toString().isEmpty() && answer4.getText().toString().isEmpty() && correctAnswer.getText().toString().isEmpty()
                && difficultyLevel.getText().toString().isEmpty()){
            Toast.makeText(AddQuestionActivity.this,"All fields must be field!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}