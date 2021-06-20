package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class Education extends AppCompatActivity {
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;
    public DiscreteSeekBar pri;
    public DiscreteSeekBar sec;
    public DiscreteSeekBar high;
    public DiscreteSeekBar nat_curr;
    public DiscreteSeekBar res_spe;

    public Button record;
    public User userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();
        record=(Button) findViewById(R.id.record);

        pri=(DiscreteSeekBar) findViewById(R.id.primary);
        sec=(DiscreteSeekBar) findViewById(R.id.secondary);
        high=(DiscreteSeekBar) findViewById(R.id.higher);
        nat_curr=(DiscreteSeekBar) findViewById(R.id.national_curriculum);
        res_spe=(DiscreteSeekBar) findViewById(R.id.research_spending);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    pri.setProgress(userProfile.prim_edu);
                    sec.setProgress(userProfile.sec_edu);
                    high.setProgress(userProfile.hig_edu);
                    nat_curr.setProgress(userProfile.nat_curr);
                    res_spe.setProgress(userProfile.res_spe);

                    Log.i("TAG","Farmer="+String.valueOf(userProfile.farmer_sub));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile.prim_edu=pri.getProgress();
                userProfile.sec_edu=sec.getProgress();
                userProfile.hig_edu=high.getProgress();
                userProfile.nat_curr=nat_curr.getProgress();
                userProfile.res_spe=res_spe.getProgress();
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            startActivity(new Intent(Education.this,LandingPage.class));

                        }
                        else
                        {

                            Toast.makeText(Education.this,"Error occured",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }
}