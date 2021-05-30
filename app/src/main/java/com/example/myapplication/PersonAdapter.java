package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    Context context;
    List<Person> persons;

    public PersonAdapter(Context context, List<Person> persons){
        this.context = context;
        this.persons = persons;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //ImageView imageView;
        TextView username;
        TextView password;
        ToggleButton btn;

        public MyViewHolder(View view){
            super(view);

            //imageView = view.findViewById(R.id.personImage);
            username = view.findViewById(R.id.username);
            password = view.findViewById(R.id.password);
            //btn = view.findViewById(R.id.toggleButton);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_card,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.username.setText(persons.get(position).getUserName());
        holder.password.setText(persons.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }



}
