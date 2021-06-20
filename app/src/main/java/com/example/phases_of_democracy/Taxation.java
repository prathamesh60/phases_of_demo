package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Taxation extends AppCompatActivity {
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;
    public DiscreteSeekBar low;
    public DiscreteSeekBar mid;
    public DiscreteSeekBar high;
    public DiscreteSeekBar sal;
    public DiscreteSeekBar bus;
    public DiscreteSeekBar corp;
    public DiscreteSeekBar cap;
    public DiscreteSeekBar inhe;
    public DiscreteSeekBar prop;

    public Button record;
    public User userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxation);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();
        record=(Button) findViewById(R.id.record);
        low=(DiscreteSeekBar) findViewById(R.id.low_income_tax);
        mid=(DiscreteSeekBar) findViewById(R.id.middle_income_tax);
        high=(DiscreteSeekBar) findViewById(R.id.high_income_tax);
        sal=(DiscreteSeekBar) findViewById(R.id.sales_tax);
        bus=(DiscreteSeekBar) findViewById(R.id.business_tax);
        corp=(DiscreteSeekBar) findViewById(R.id.corporation_tax);
        cap=(DiscreteSeekBar) findViewById(R.id.capital_gains_tax);
        inhe=(DiscreteSeekBar) findViewById(R.id.inheritance_tax);
        prop=(DiscreteSeekBar) findViewById(R.id.property_tax);
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    low.setProgress(userProfile.low_income);
                    mid.setProgress(userProfile.middle_income);
                    high.setProgress(userProfile.high_income);
                    sal.setProgress(userProfile.sal_tax);
                    bus.setProgress(userProfile.bus_tax);
                    corp.setProgress(userProfile.corporate_tax);
                    cap.setProgress(userProfile.cap_gain);
                    inhe.setProgress(userProfile.inh_tex);
                    prop.setProgress(userProfile.prop_tax);




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile.low_income=low.getProgress();
                userProfile.middle_income=mid.getProgress();
                userProfile.high_income=high.getProgress();
                userProfile.sal_tax=sal.getProgress();
                userProfile.bus_tax=bus.getProgress();
                userProfile.corporate_tax=corp.getProgress();
                userProfile.cap_gain=cap.getProgress();
                userProfile.inh_tex=inhe.getProgress();
                userProfile.prop_tax=prop.getProgress();


                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            startActivity(new Intent(Taxation.this,LandingPage.class));

                        }
                        else
                        {

                            Toast.makeText(Taxation.this,"Error occured",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
    }
}