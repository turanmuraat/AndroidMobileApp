package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {
    static final String TAG = "QuestionAdapter";
    Context context;
    List<Question> questions;
    String currentUser;

    public QuestionAdapter(Context context, List<Question> questions, String currentUser){
        this.context = context;
        this.questions = questions;
        this.currentUser = currentUser;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView question;
        TextView answer1;
        TextView answer2;
        TextView answer3;
        TextView answer4;
        TextView correctAnswer;
        Button btn_delete;
        Button btn_update;

        public MyViewHolder(View view){
            super(view);
            question = view.findViewById(R.id.question);
            answer1 = view.findViewById(R.id.answer1);
            answer2 = view.findViewById(R.id.answer2);
            answer3 = view.findViewById(R.id.answer3);
            answer4 = view.findViewById(R.id.answer4);
            correctAnswer = view.findViewById(R.id.correctAnswer);
            btn_delete = view.findViewById(R.id.btn_delete);
            btn_update = view.findViewById(R.id.btn_update);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_card,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if(getItemCount() == 0)
            Toast.makeText(context,"There is no any question!",Toast.LENGTH_SHORT).show();
        holder.question.setText(questions.get(position).getQuestion());
        holder.answer1.setText(questions.get(position).getAnswer1());
        holder.answer2.setText(questions.get(position).getAnswer2());
        holder.answer3.setText(questions.get(position).getAnswer3());
        holder.answer4.setText(questions.get(position).getAnswer4());
        holder.correctAnswer.setText(questions.get(position).getCorrectAnswer());
        holder.btn_delete.setOnClickListener((v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Question will delete!")
                    .setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Question.deleteSelectedQuestion(questions.get(position).getQuestionId());
                            Toast.makeText(context,"Question is deleted!",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, getItemCount());
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            }));
        holder.btn_update.setOnClickListener((v -> {
            Intent intent = new Intent(context,UpdateQuestionActivity.class);
            intent.putExtra("questionId",questions.get(position).getQuestionId());
            intent.putExtra("question",questions.get(position).getQuestion());
            intent.putExtra("answer1",questions.get(position).getAnswer1());
            intent.putExtra("answer2",questions.get(position).getAnswer2());
            intent.putExtra("answer3",questions.get(position).getAnswer3());
            intent.putExtra("answer4",questions.get(position).getAnswer4());
            intent.putExtra("correctAnswer",questions.get(position).getCorrectAnswer());
            intent.putExtra("difficultyLevel",questions.get(position).getDifficultyLevel());
            intent.putExtra("username",currentUser);
            context.startActivity(intent);

        }));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


}
