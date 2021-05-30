package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    CardView addQuestion, listQuestions, createExam, examSettings;
    String currentUser;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentUser = getIntent().getStringExtra("username");
        currentUserId = getIntent().getStringExtra("userId");
        addQuestion = (CardView) findViewById(R.id.addQuestionsCard);
        listQuestions = (CardView) findViewById(R.id.listQuestionsCard);
        createExam = (CardView) findViewById(R.id.createExamCard);
        examSettings = (CardView) findViewById(R.id.examSettingsCard);

        addQuestion.setOnClickListener(this);
        listQuestions.setOnClickListener(this);
        createExam.setOnClickListener(this);
        examSettings.setOnClickListener(this);

        Toast.makeText(this, "Welcome " + currentUser, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.addQuestionsCard:
                intent = new Intent(v.getContext(), AddQuestionActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("userId",currentUserId);
                startActivity(intent);
                break;
            case R.id.listQuestionsCard:
                intent = new Intent(v.getContext(), RecyclerViewActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("userId",currentUserId);
                startActivity(intent);
                break;
            case R.id.createExamCard:
                intent = new Intent(v.getContext(), CreateExamActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("userId",currentUserId);
                startActivity(intent);
                break;
            case R.id.examSettingsCard:
                intent = new Intent(v.getContext(), SettingsActivity.class);
                intent.putExtra("username", currentUser);
                intent.putExtra("userId",currentUserId);
                startActivity(intent);
                break;
        }
    }
}
