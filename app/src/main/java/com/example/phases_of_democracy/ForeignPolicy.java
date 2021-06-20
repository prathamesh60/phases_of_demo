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

public class ForeignPolicy extends AppCompatActivity {
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;
    public DiscreteSeekBar f_s;
    public DiscreteSeekBar m_w;
    public DiscreteSeekBar s_s;
    public Button record;
    public User userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_policy);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();
        record=(Button) findViewById(R.id.record);
        f_s=(DiscreteSeekBar) findViewById(R.id.defense_spending);
        m_w=(DiscreteSeekBar) findViewById(R.id.tarrif_on_imports);
        s_s=(DiscreteSeekBar) findViewById(R.id.border_control);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    f_s.setProgress(userProfile.def_spe);
                    m_w.setProgress(userProfile.tar_imp);
                    s_s.setProgress(userProfile.bor_cont);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile.def_spe=f_s.getProgress();
                userProfile.tar_imp=m_w.getProgress();
                userProfile.bor_cont=s_s.getProgress();

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            startActivity(new Intent(ForeignPolicy.this,LandingPage.class));

                        }
                        else
                        {

                            Toast.makeText(ForeignPolicy.this,"Error occured",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }
}