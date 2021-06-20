package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private EditText email;
    private TextView login;
    private Button register;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        name=(EditText) findViewById(R.id.name);
        password=(EditText) findViewById(R.id.password);
        email=(EditText) findViewById(R.id.email);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        register=(Button) findViewById(R.id.register);
        login=(TextView) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String username=name.getText().toString().trim();
               String useremail=email.getText().toString().trim();
               String userpassword=password.getText().toString().trim();

               if(username.isEmpty())
               {
                   name.setError("Name is required");
                   name.requestFocus();
                   return;
               }
               if(useremail.isEmpty()){
                   email.setError("Email Required");
                   email.requestFocus();
                   return;
               }
               if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                   email.setError("Please enter valid email");
                   email.requestFocus();
                   return;
               }
               if(userpassword.isEmpty()){
                   password.setError("Password is required");
                   password.requestFocus();
                   return;
               }
               if(userpassword.length()<6)
               {
                   password.setError("Password must be of minimum 6 characters");
                   password.requestFocus();
                   return;
               }
              progressBar.setVisibility(view.VISIBLE);
               mAuth.createUserWithEmailAndPassword(useremail,userpassword)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                  User userProfile=new User(username,useremail,userpassword,1,3,2,1,2,1,3,2,1,3,2,3,3,2,3,2,2,3,2,1,1,2,3,4,1,1,1,1,3,2,1,23100, (float)32.51,0);
                                   FirebaseDatabase.getInstance().getReference("Users")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful())
                                           {
                                               Toast.makeText(Register.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                                               GlobalClass globalClass=(GlobalClass) getApplicationContext();
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
                                               startActivity(new Intent(Register.this,PopActivity.class));
                                               progressBar.setVisibility(view.INVISIBLE);
                                           }
                                           else
                                           {

                                               Toast.makeText(Register.this,"Failed to Register",Toast.LENGTH_LONG).show();
                                               progressBar.setVisibility(view.INVISIBLE);
                                           }
                                       }
                                   });
                               }
                               else{
                                   Toast.makeText(Register.this,"Failed to Register! Try again",Toast.LENGTH_LONG).show();
                                   progressBar.setVisibility(view.INVISIBLE);
                               }
                           }
                       });
           }
       }

     );
    }
}