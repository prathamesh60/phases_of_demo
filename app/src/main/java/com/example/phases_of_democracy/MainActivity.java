package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public TextView startNew;
    public TextView login;
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;

    //private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       if(FirebaseAuth.getInstance().getCurrentUser()!=null)
       {  ProgressDialog dialog;
           dialog = new ProgressDialog(MainActivity.this);
           dialog.setTitle("Please Wait...");
           dialog.setMessage("Loading your Data");
           dialog.show();
           GlobalClass globalClass=(GlobalClass) getApplicationContext();
           user=FirebaseAuth.getInstance().getCurrentUser();
           reference= FirebaseDatabase.getInstance().getReference("Users");
           userId=user.getUid();
           reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   User userProfile=snapshot.getValue(User.class);
                   if(userProfile!=null)
                   {
                       globalClass.setUsername(userProfile.username);
                       globalClass.setUseremail(userProfile.useremail);
                       globalClass.setPassword(userProfile.password);
                       globalClass.setRevenue(userProfile.revenue);
                       globalClass.setPopularity(userProfile.popularity);
                       globalClass.setFarmer_sub(userProfile.farmer_sub);
                       globalClass.setMiddle_income(userProfile.minimum_wage);
                       globalClass.setStimulus_spending(userProfile.stimulus_spending);
                       globalClass.setEnv_spending(userProfile.env_spending);
                       globalClass.setCarbon_tax(userProfile.carbon_tax);
                       globalClass.setPrim_edu(userProfile.prim_edu);
                       globalClass.setSec_edu(userProfile.sec_edu);
                       globalClass.setHig_edu(userProfile.hig_edu);
                       globalClass.setNat_curr(userProfile.nat_curr);
                       globalClass.setRes_spe(userProfile.res_spe);
                       globalClass.setDef_spe(userProfile.def_spe);
                       globalClass.setTar_imp(userProfile.tar_imp);
                       globalClass.setBor_cont(userProfile.bor_cont);
                       globalClass.setHeal_spe(userProfile.heal_spe);
                       globalClass.setVacci(userProfile.vacci);
                       globalClass.setBirth_con(userProfile.birth_con);
                       globalClass.setInfra(userProfile.infra);
                       globalClass.setToll_load(userProfile.toll_load);
                       globalClass.setLaw_enf(userProfile.law_enf);
                       globalClass.setDis_man(userProfile.dis_man);
                       globalClass.setLow_income(userProfile.low_income);
                       globalClass.setMiddle_income(userProfile.middle_income);
                       globalClass.setHigh_income(userProfile.high_income);
                       globalClass.setSal_tax(userProfile.sal_tax);
                       globalClass.setBus_tax(userProfile.bus_tax);
                       globalClass.setCorporate_tax(userProfile.corporate_tax);
                       globalClass.setCap_gain(userProfile.cap_gain);
                       globalClass.setInh_tex(userProfile.inh_tex);
                       globalClass.setProp_tax(userProfile.prop_tax);
                       globalClass.setUnemp_benefits(userProfile.unemp_benefits);
                       globalClass.setPension(userProfile.pension);
                       globalClass.setEvent_no(userProfile.event_no);
                       globalClass.setTree_plantation(userProfile.tree_plantation);
                       globalClass.setSustainable_development(userProfile.sustainable_development);
                       globalClass.setRenewable_energy(userProfile.renewable_energy);
                       globalClass.setHealth_awareness(userProfile.health_awareness);
                       globalClass.setHealth_infra(userProfile.health_infra);

                       startActivity(new Intent(MainActivity.this,LandingPage.class));
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                   Toast.makeText(MainActivity.this,"Some error occured",Toast.LENGTH_SHORT).show();
               }
           });

       }

        startNew=(TextView) findViewById(R.id.newgame);
        login= (TextView)  findViewById(R.id.login);
        startNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this,LandingPage.class));]
                startActivity(new Intent(MainActivity.this,Register.class));
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });

    }


}