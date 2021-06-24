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

public class Environment extends AppCompatActivity {
   public  DiscreteSeekBar environmental_spending;
   public  DiscreteSeekBar carbon_tax;
   public DiscreteSeekBar tree_plan;
   public DiscreteSeekBar sustain_dev;
   public DiscreteSeekBar ren_ener;

    public FirebaseUser user;

    public DatabaseReference reference;
    private String userId;

    public Button record;
    public User userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);


        environmental_spending=(DiscreteSeekBar) findViewById(R.id.public_parks_speaking);
        carbon_tax=(DiscreteSeekBar) findViewById(R.id.carbon_tax);
        tree_plan=(DiscreteSeekBar) findViewById(R.id.tree_plantation);
        sustain_dev=(DiscreteSeekBar) findViewById(R.id.sustainable_development);
        ren_ener=(DiscreteSeekBar) findViewById(R.id.renewable_energy);

        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();

        record=(Button) findViewById(R.id.record);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    environmental_spending.setProgress(userProfile.env_spending);
                    carbon_tax.setProgress(userProfile.carbon_tax);
                    tree_plan.setProgress(userProfile.tree_plantation);
                    sustain_dev.setProgress(userProfile.sustainable_development);
                    ren_ener.setProgress(userProfile.renewable_energy);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile.env_spending=environmental_spending.getProgress();
                userProfile.carbon_tax=carbon_tax.getProgress();
                userProfile.tree_plantation=tree_plan.getProgress();
                userProfile.sustainable_development=sustain_dev.getProgress();
                userProfile.renewable_energy=ren_ener.getProgress();

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            startActivity(new Intent(Environment.this,LandingPage.class));

                        }
                        else
                        {

                            Toast.makeText(Environment.this,"Error occured",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });



    }

}