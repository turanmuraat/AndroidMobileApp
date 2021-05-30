package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    static final String TAG = "RecyclerView";
    androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager;
    List<Question> questionList;
    String currentUser;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    QuestionAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        defineVariables();

    }

    public void defineVariables(){
        currentUser = getIntent().getStringExtra("username");
        recyclerView = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        questionList = new ArrayList<>();
        questionList = Question.getQuestionList(currentUser);
        adp = new QuestionAdapter(this,questionList,currentUser);
        recyclerView.setAdapter(adp);
    }



}