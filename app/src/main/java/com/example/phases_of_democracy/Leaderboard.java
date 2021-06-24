package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends AppCompatActivity {
   public DatabaseReference mDatabaseReference;
   public ListView listView;

   public  List<score> scorelist;
   public User userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        scorelist=new ArrayList<>();
        listView=(ListView) findViewById(R.id.list);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                     userProfile= postSnapshot.getValue(User.class);
                     float s= userProfile.popularity;
                     String uname=userProfile.username;
                     score s1=new score(uname,s);
                     scorelist.add(s1);
                     MyListAdaptor adaptor=new MyListAdaptor(getApplicationContext(),R.layout.my_custom_list,scorelist);

                     listView.setAdapter(adaptor);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}