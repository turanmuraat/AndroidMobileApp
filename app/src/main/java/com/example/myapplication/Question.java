package com.example.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Question {
    static final String TAG = "Question";
    private String questionId;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private String difficultyLevel;
    private String username;
    static DatabaseReference mDatabase;
    static DatabaseReference questionCloudEndPoint;

    public Question(){

    }

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, String difficultyLevel, String username) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
        this.username = username;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) { this.difficultyLevel = difficultyLevel; }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static List<Question> getQuestionList(String currentUser){
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        questionCloudEndPoint = mDatabase.child("Question");
        List<Question> mEntries = new ArrayList<>();
        Query query = questionCloudEndPoint.orderByChild("username").equalTo(currentUser);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()){
                    Question note = noteSnapshot.getValue(Question.class);
                    String temp = note.toString();
                    Log.d("note: ", temp + "\n"); // can log all
                    mEntries.add(note);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });
        return mEntries;
    }


    public static void addDataToFirebase(Question question) {
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        questionCloudEndPoint = mDatabase.child("Question");
        String key = questionCloudEndPoint.push().getKey();
        question.setQuestionId(key);
        questionCloudEndPoint.child(key).setValue(question);
    }

    public static void deleteSelectedQuestion(String questionId){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = mDatabase.child("Question").orderByChild("questionId").equalTo(questionId);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot questionSnapshot: dataSnapshot.getChildren()) {
                    questionSnapshot.getRef().removeValue();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    public static void updateQuestionById(Question question) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Question");
        mDatabase.child(question.getQuestionId()).setValue(question);
    }
}
