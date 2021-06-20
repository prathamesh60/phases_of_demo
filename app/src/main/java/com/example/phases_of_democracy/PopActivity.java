package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;

public class PopActivity extends AppCompatActivity {
    public ProgressDialog dialog;
    public EditText eventi;
    public String e;
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;
    public int event_no;
    public Button okButton;
    public User userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        eventi=(EditText) findViewById(R.id.event);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    event_no=userProfile.event_no;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dialog = new ProgressDialog(PopActivity.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("Loading the information");
        dialog.show();
        e="";
        okButton=(Button) findViewById(R.id.ok);
        Executors.newSingleThreadExecutor().execute(() -> {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            JSONObject jsonObject = JsonParserEvent.getDataFromWeb();
            try {
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("Sheet1");

                        /**
                         * Check Length of Array...
                         */
                        JSONObject innerObject=array.getJSONObject(event_no);
                        String event=innerObject.getString("Event");
                        e=e+event;

                      //  int lenArray = array.length();
                       /*
                        if(lenArray > 0) {
                            int jIndex=0;
                            Log.i("Tagging","Entered");
                            for( ; jIndex < lenArray; jIndex++) {





                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String date = innerObject.getString("Date");
                                String event= innerObject.getString("Event");
                                String No=innerObject.getString("No");

                                Log.i("TAG",date);
                                Log.i("TAG",event);
                                Log.i("TAG",No);
                                int n=Integer.parseInt(No);
                                if(n==2)
                                {
                                    e=e+event;
                                }




                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

//                            model.setName(name);
//                            model.setCountry(country);


//                            list.add(model);
                            }
                        } */
                    }
                }
            }
            catch (JSONException je)
            {
                Log.i(JsonParserEvent.TAG, "" + je.getLocalizedMessage());
                Log.i("TAG","Exception caught");
            }
            //sync calculations

            mainHandler.post(() -> {
                dialog.dismiss();
                eventi.setText(e);
            });

            //other sync calcs.



        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile.event_no=userProfile.event_no+1;
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                           // Toast.makeText(Register.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
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

                            startActivity(new Intent(PopActivity.this,LandingPage.class));

                        }
                        else
                        {

                            Toast.makeText(PopActivity.this,"Error occured",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

    }


}