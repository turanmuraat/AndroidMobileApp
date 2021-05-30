package com.example.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    static final String TAG = "Person";
    private String personId;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String birthDate;
    private String phoneNumber;
    static DatabaseReference mDatabase;
    static DatabaseReference personCloudEndPoint;

    public Person(){

    }
    public Person(String username, String password, String name, String surname, String birthDate, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSurname() { return surname; }
    public String getBirthDate() { return birthDate; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPersonId() { return personId; }
    public void setPersonId(String personId) { this.personId = personId;}
    public void setUserName(String username) { this.username = username;}
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setPassword(String password) { this.password = password; }

    /*public static ArrayList<Person> getPersonList() {
        ArrayList<Person> persons =  new ArrayList<Person>();
        persons.add(new Person("test","123","test","test","01.02.2001","sadja@asas.com","05362717281"));
        return persons;
    }*/

    public static List<Person> getPersonList(){
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        personCloudEndPoint = mDatabase.child("Person");
        List<Person> mEntries = new ArrayList<>();
        personCloudEndPoint.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()){
                    Person note = noteSnapshot.getValue(Person.class);
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

    public static void addDataToFirebase(Person personEntry) {
        String key = personCloudEndPoint.push().getKey();
        personEntry.setPersonId(key);
        personCloudEndPoint.child(key).setValue(personEntry);
    }

    public String toString() {
        return "Person(" + username + "," ;
    }

}
