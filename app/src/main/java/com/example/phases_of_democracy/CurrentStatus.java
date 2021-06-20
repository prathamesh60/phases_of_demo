package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class CurrentStatus extends AppCompatActivity {
    public EditText revenue;
    public EditText popularity;
    public Button ok;
    public ProgressDialog dialog;
    public float pop;
    public float rev;
    public float  sum;
    public User userProfile;
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;
    public float FinalPop;
    public float FinalRev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_status);
        revenue=(EditText) findViewById(R.id.revenue);
        popularity=(EditText) findViewById(R.id.popularity);
        ok=(Button)  findViewById(R.id.ok);

        GlobalClass globalClass=(GlobalClass) getApplicationContext();
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialog = new ProgressDialog(CurrentStatus.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("Loading the information");
        dialog.show();
        sum=0;
        Executors.newSingleThreadExecutor().execute(() -> {

            Handler mainHandler = new Handler(Looper.getMainLooper());
            JSONObject jsonObject = JsonParserPopularity.getDataFromWeb();
            try {
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    Log.i("TAG","Array is not empty");
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("Sheet1");

                        /**
                         * Check Length of Array...
                         */
                        JSONObject innerObject=array.getJSONObject(0);
                        String weight=innerObject.getString("weightage");
                        String val=innerObject.getString("t"+(userProfile.farmer_sub));

                        int w=Integer.parseInt(weight);
                        int v=Integer.parseInt(val);
                        sum=sum+w*v;
                        Log.i("TAG","w="+weight);
                        Log.i("TAG","v="+val);

                        JSONObject innerObject1=array.getJSONObject(1);
                         weight=innerObject1.getString("weightage");
                         val=innerObject1.getString("t"+(userProfile.minimum_wage));

                         w=Integer.parseInt(weight);
                         v=Integer.parseInt(val);
                         sum=sum+w*v;

                        JSONObject innerObject2=array.getJSONObject(2);
                        weight=innerObject2.getString("weightage");
                        val=innerObject2.getString("t"+(userProfile.stimulus_spending));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject3=array.getJSONObject(3);
                        weight=innerObject3.getString("weightage");
                        val=innerObject3.getString("t"+(userProfile.carbon_tax));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject4=array.getJSONObject(4);
                        weight=innerObject4.getString("weightage");
                        val=innerObject4.getString("t"+(userProfile.env_spending));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject5=array.getJSONObject(5);
                        weight=innerObject5.getString("weightage");
                        val=innerObject5.getString("t"+(userProfile.prim_edu));
                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject6=array.getJSONObject(6);
                        weight=innerObject6.getString("weightage");
                        val=innerObject6.getString("t"+(userProfile.sec_edu));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject7=array.getJSONObject(7);
                        weight=innerObject7.getString("weightage");
                        val=innerObject7.getString("t"+(userProfile.hig_edu));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject8=array.getJSONObject(8);
                        weight=innerObject8.getString("weightage");
                        val=innerObject8.getString("t"+(userProfile.res_spe));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;
                        JSONObject innerObject9=array.getJSONObject(9);
                        weight=innerObject9.getString("weightage");
                        val=innerObject9.getString("t"+(userProfile.nat_curr));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;
                        JSONObject innerObject10=array.getJSONObject(10);
                        weight=innerObject10.getString("weightage");
                        val=innerObject10.getString("t"+(userProfile.def_spe));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;
                        JSONObject innerObject11=array.getJSONObject(11);
                        weight=innerObject11.getString("weightage");
                        val=innerObject11.getString("t"+(userProfile.tar_imp));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;
                        JSONObject innerObject12=array.getJSONObject(12);
                        weight=innerObject12.getString("weightage");
                        val=innerObject12.getString("t"+(userProfile.bor_cont));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject13=array.getJSONObject(13);
                        weight=innerObject13.getString("weightage");
                        val=innerObject13.getString("t"+(userProfile.heal_spe));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject14=array.getJSONObject(14);
                        weight=innerObject14.getString("weightage");
                        val=innerObject14.getString("t"+(userProfile.vacci));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject15=array.getJSONObject(15);
                        weight=innerObject15.getString("weightage");
                        val=innerObject15.getString("t"+(userProfile.birth_con));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject16=array.getJSONObject(16);
                        weight=innerObject16.getString("weightage");
                        val=innerObject16.getString("t"+(userProfile.infra));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject17=array.getJSONObject(17);
                        weight=innerObject17.getString("weightage");
                        val=innerObject17.getString("t"+(userProfile.toll_load));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject18=array.getJSONObject(18);
                        weight=innerObject18.getString("weightage");
                        val=innerObject18.getString("t"+(userProfile.law_enf));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;


                        JSONObject innerObject19=array.getJSONObject(19);
                        weight=innerObject19.getString("weightage");
                        val=innerObject19.getString("t"+(userProfile.dis_man));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject20=array.getJSONObject(20);
                        weight=innerObject20.getString("weightage");
                        val=innerObject20.getString("t"+(userProfile.low_income));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject21=array.getJSONObject(21);
                        weight=innerObject21.getString("weightage");
                        val=innerObject21.getString("t"+(userProfile.middle_income));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;
                        JSONObject innerObject22=array.getJSONObject(22);
                        weight=innerObject22.getString("weightage");
                        val=innerObject22.getString("t"+(userProfile.high_income));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject23=array.getJSONObject(23);
                        weight=innerObject23.getString("weightage");
                        val=innerObject23.getString("t"+(userProfile.sal_tax));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject24=array.getJSONObject(24);
                        weight=innerObject24.getString("weightage");
                        val=innerObject24.getString("t"+(userProfile.bus_tax));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;
                        JSONObject innerObject25=array.getJSONObject(25);
                        weight=innerObject25.getString("weightage");
                        val=innerObject25.getString("t"+(userProfile.corporate_tax));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject26=array.getJSONObject(26);
                        weight=innerObject26.getString("weightage");
                        val=innerObject26.getString("t"+(userProfile.cap_gain));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject27=array.getJSONObject(27);
                        weight=innerObject27.getString("weightage");
                        val=innerObject27.getString("t"+(userProfile.inh_tex));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject28=array.getJSONObject(28);
                        weight=innerObject28.getString("weightage");
                        val=innerObject28.getString("t"+(userProfile.prop_tax));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject29=array.getJSONObject(29);
                        weight=innerObject29.getString("weightage");
                        val=innerObject29.getString("t"+(userProfile.unemp_benefits));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        JSONObject innerObject30=array.getJSONObject(30);
                        weight=innerObject30.getString("weightage");
                        val=innerObject30.getString("t"+(userProfile.pension));

                        w=Integer.parseInt(weight);
                        v=Integer.parseInt(val);
                        sum=sum+w*v;

                        FinalPop=sum/231;
                        Log.i("TAG","Popularity="+FinalPop);
                        Log.i("TAG","Popularity="+sum);

                        String rang="Range";
                        if(FinalPop< 10)
                        {
                            rang=rang+"1";
                        }
                        else if(FinalPop>=10 && FinalPop<25)
                        {
                            rang=rang+"2";
                        }
                        else if(FinalPop>=25 && FinalPop<30)
                        {
                            rang=rang+"3";
                        }
                        else if(FinalPop>=30 && FinalPop<32)
                        {
                            rang=rang+"4";
                        }
                        else if(FinalPop>=32 && FinalPop<34)
                        {
                            rang=rang+"5";
                        }
                        else if(FinalPop>=34 && FinalPop<36)
                        {
                            rang=rang+"6";
                        }
                        else if(FinalPop>=36 && FinalPop<38)
                        {
                            rang=rang+"7";
                        }
                        else if(FinalPop>=38 && FinalPop<40)
                        {
                            rang=rang+"8";
                        }
                        else if(FinalPop>=40 && FinalPop<45)
                        {
                            rang=rang+"9";
                        }
                        else if(FinalPop>=45 && FinalPop<60)
                        {
                            rang=rang+"10";
                        }
                        else if(FinalPop>=60)
                        {
                            rang=rang+"11";
                        }

                        FinalRev=userProfile.revenue;

                        if(userProfile.farmer_sub!=globalClass.getFarmer_sub())
                        {  //String change=innerObject.getString("Cash");
                           String reduction=innerObject.getString(rang);
                           String share=innerObject.getString("Share");
                           int r=Integer.parseInt(reduction);
                           int s=Integer.parseInt(share);
                           if(userProfile.farmer_sub>globalClass.getFarmer_sub())
                           {
                               float c=r*s;
                               c/=100;
                               FinalRev=FinalRev-c;
                           }
                           else
                           {
                               float c=r*s;
                               c/=100;
                               FinalRev=FinalRev+c;
                           }
                        }
                        if(userProfile.minimum_wage!=globalClass.getMinimum_wage())
                        {
                            String reduction=innerObject1.getString(rang);
                            String share=innerObject1.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.minimum_wage>globalClass.getMinimum_wage())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.stimulus_spending!=globalClass.getStimulus_spending())
                        {
                            String reduction=innerObject2.getString(rang);
                            String share=innerObject2.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.stimulus_spending>globalClass.getStimulus_spending())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.carbon_tax!=globalClass.getCarbon_tax())
                        {
                            String reduction=innerObject3.getString(rang);
                            String share=innerObject3.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.carbon_tax>globalClass.getCarbon_tax())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }

                        }
                        if(userProfile.env_spending!=globalClass.getEnv_spending())
                        {
                            String reduction=innerObject4.getString(rang);
                            String share=innerObject4.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.env_spending>globalClass.getEnv_spending())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.prim_edu!=globalClass.getPrim_edu())
                        {
                            String reduction=innerObject5.getString(rang);
                            String share=innerObject5.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.prim_edu>globalClass.getPrim_edu())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.sec_edu!=globalClass.getSec_edu())
                        {
                            String reduction=innerObject6.getString(rang);
                            String share=innerObject6.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.sec_edu>globalClass.getSec_edu())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.hig_edu!=globalClass.getHig_edu())
                        {
                            String reduction=innerObject7.getString(rang);
                            String share=innerObject7.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.hig_edu>globalClass.getHig_edu())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.res_spe!=globalClass.getRes_spe())
                        {
                            String reduction=innerObject8.getString(rang);
                            String share=innerObject8.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.res_spe>globalClass.getRes_spe())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.nat_curr!=globalClass.getNat_curr())
                        {
                            String reduction=innerObject9.getString(rang);
                            String share=innerObject9.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.nat_curr>globalClass.getNat_curr())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.def_spe!=globalClass.getDef_spe())
                        {
                            String reduction=innerObject10.getString(rang);
                            String share=innerObject10.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.def_spe>globalClass.getDef_spe())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.tar_imp!=globalClass.getTar_imp())
                        {
                            String reduction=innerObject11.getString(rang);
                            String share=innerObject11.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.tar_imp>globalClass.getTar_imp())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }

                        }
                        if(userProfile.bor_cont!=globalClass.getBor_cont())
                        {
                            String reduction=innerObject12.getString(rang);
                            String share=innerObject12.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.bor_cont>globalClass.getBor_cont())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.heal_spe!=globalClass.getHeal_spe())
                        {
                            String reduction=innerObject13.getString(rang);
                            String share=innerObject13.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.heal_spe>globalClass.getHeal_spe())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.vacci!=globalClass.getVacci())
                        {
                            String reduction=innerObject14.getString(rang);
                            String share=innerObject14.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.vacci>globalClass.getVacci())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }

                        }
                        if(userProfile.birth_con!=globalClass.getBirth_con())
                        {
                            String reduction=innerObject15.getString(rang);
                            String share=innerObject15.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.birth_con>globalClass.getBirth_con())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.infra!=globalClass.getInfra())
                        {
                            String reduction=innerObject16.getString(rang);
                            String share=innerObject16.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.infra>globalClass.getInfra())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.toll_load!=globalClass.getToll_load())
                        {
                            String reduction=innerObject17.getString(rang);
                            String share=innerObject17.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.toll_load>globalClass.getToll_load())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.law_enf!=globalClass.getLaw_enf())
                        {
                            String reduction=innerObject18.getString(rang);
                            String share=innerObject18.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.law_enf>globalClass.getLaw_enf())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.dis_man!=globalClass.getDis_man())
                        {
                            String reduction=innerObject19.getString(rang);
                            String share=innerObject19.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.dis_man>globalClass.getDis_man())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.low_income!=globalClass.getLow_income())
                        {
                            String reduction=innerObject20.getString(rang);
                            String share=innerObject20.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.low_income>globalClass.getLow_income())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.middle_income!=globalClass.getMiddle_income())
                        {
                            String reduction=innerObject21.getString(rang);
                            String share=innerObject21.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.middle_income>globalClass.getMiddle_income())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.high_income!=globalClass.getHigh_income())
                        {
                            String reduction=innerObject22.getString(rang);
                            String share=innerObject22.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.high_income>globalClass.getHigh_income())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.sal_tax!=globalClass.getSal_tax())
                        {
                            String reduction=innerObject23.getString(rang);
                            String share=innerObject23.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.sal_tax>globalClass.getSal_tax())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.bus_tax!=globalClass.getBus_tax())
                        {
                            String reduction=innerObject24.getString(rang);
                            String share=innerObject24.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.bus_tax>globalClass.getBus_tax())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.corporate_tax!=globalClass.getCorporate_tax())
                        {
                            String reduction=innerObject25.getString(rang);
                            String share=innerObject25.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.corporate_tax>globalClass.getCorporate_tax())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.cap_gain!=globalClass.getCap_gain())
                        {
                            String reduction=innerObject26.getString(rang);
                            String share=innerObject26.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.cap_gain>globalClass.getCap_gain())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }
                        if(userProfile.inh_tex!=globalClass.getInh_tex())
                        {
                            String reduction=innerObject27.getString(rang);
                            String share=innerObject27.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.inh_tex>globalClass.getInh_tex())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }

                        if(userProfile.prop_tax!=globalClass.getProp_tax())
                        {
                            String reduction=innerObject28.getString(rang);
                            String share=innerObject28.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.prop_tax>globalClass.getProp_tax())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                        }

                        if(userProfile.unemp_benefits!=globalClass.getUnemp_benefits())
                        {
                            String reduction=innerObject29.getString(rang);
                            String share=innerObject29.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.unemp_benefits>globalClass.getUnemp_benefits())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }
                        if(userProfile.pension!=globalClass.getPension())
                        {
                            String reduction=innerObject30.getString(rang);
                            String share=innerObject30.getString("Share");
                            int r=Integer.parseInt(reduction);
                            int s=Integer.parseInt(share);
                            if(userProfile.pension>globalClass.getPension())
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev-c;
                            }
                            else
                            {
                                float c=r*s;
                                c/=100;
                                FinalRev=FinalRev+c;
                            }
                        }


                    }
                }
            }
            catch (JSONException je)
            {
                Log.i(JsonParserPopularity.TAG, "" + je.getLocalizedMessage());
                Log.i("TAG","Exception caught");
            }
            //sync calculations

            mainHandler.post(() -> {

                if(FinalPop<25)
                {
                       startActivity(new Intent(CurrentStatus.this,GameOver1.class));
                }
                if(FinalRev <23100 && 23100-FinalRev>4620)
                {
                    startActivity(new Intent(CurrentStatus.this,GameOver2.class));

                }

                dialog.dismiss();
                popularity.setText(String.valueOf(FinalPop));
                revenue.setText(String.valueOf(FinalRev));

                userProfile.popularity=FinalPop;
                userProfile.revenue=FinalRev;
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {



                        }
                        else
                        {

                            Toast.makeText(CurrentStatus.this,"Error occured",Toast.LENGTH_LONG).show();

                        }
                    }
                });




                Log.i("TAG","Sum="+String.valueOf(sum));

            });


            //other sync calcs.



        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CurrentStatus.this,PopActivity.class));
            }
        });

    }
}