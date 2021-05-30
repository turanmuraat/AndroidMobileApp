package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateQuestionActivity extends AppCompatActivity {
    String questionId;
    EditText question;
    EditText answer1;
    EditText answer2;
    EditText answer3;
    EditText answer4;
    EditText correctAnswer;
    EditText difficultyLevel;
    Button btn_update_question;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        //Toolbar toolbarForUpdate = findViewById(R.id.toolbarForUpdate);
        //setSupportActionBar(toolbarForUpdate);
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
        btn_update_question = (Button) findViewById(R.id.btn_update_question);
        setText();
    }
    private void defineListeners() {
        btn_update_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(checkFields()){
                    Question questions = new Question(question.getText().toString(),answer1.getText().toString(),
                            answer2.getText().toString(),answer3.getText().toString(),answer4.getText().toString(),
                            correctAnswer.getText().toString(),difficultyLevel.getText().toString(), currentUser);
                    questions.setQuestionId(questionId);
                    Question.updateQuestionById(questions);
                    Toast.makeText(UpdateQuestionActivity.this,"Question is updated!",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(UpdateQuestionActivity.this,"All fields must be field!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setText(){
        Intent intent = getIntent();
        question.setText(intent.getStringExtra("question"));
        answer1.setText(intent.getStringExtra("answer1"));
        answer2.setText(intent.getStringExtra("answer2"));
        answer3.setText(intent.getStringExtra("answer3"));
        answer4.setText(intent.getStringExtra("answer4"));
        correctAnswer.setText(intent.getStringExtra("correctAnswer"));
        difficultyLevel.setText(intent.getStringExtra("difficultyLevel"));
        questionId = intent.getStringExtra("questionId");
        currentUser = intent.getStringExtra("username");
    }
}