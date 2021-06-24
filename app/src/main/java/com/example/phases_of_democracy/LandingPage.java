package com.example.phases_of_democracy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LandingPage extends AppCompatActivity {
    public TextView environment;
    public  TextView education;
    public TextView foreign_policy;
    public  TextView healthcare;
    public  TextView infrastructure;
    public  TextView safety;
    public TextView taxation;
    public TextView welfare;
    public TextView economy;
    public TextView date;
    public ImageView nextbutton;
    public ImageView logout;
    public ImageView lead_boa;
    public FirebaseUser user;
    public DatabaseReference reference;
    private String userId;

    public TextView revenue;
    public TextView popu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        environment=(TextView) findViewById(R.id.environment);
        education = (TextView) findViewById(R.id.education);
        foreign_policy=(TextView) findViewById(R.id.foreign_policy);
        healthcare  =(TextView) findViewById(R.id.healthcare);
        infrastructure=(TextView) findViewById(R.id.infrastructure);
        safety=(TextView) findViewById(R.id.safety);
        taxation=(TextView) findViewById(R.id.taxation);
        welfare =(TextView) findViewById(R.id.welfare);
        economy=(TextView)  findViewById(R.id.eco_n_dev);
        nextbutton=(ImageView) findViewById(R.id.next) ;
        lead_boa=(ImageView) findViewById(R.id.leaderboard);
        logout=(ImageView)  findViewById(R.id.logout);
        revenue=(TextView) findViewById(R.id.revenue);
        popu=(TextView)  findViewById(R.id.popularity);
        date=(TextView) findViewById(R.id.date);
       String[] datestring=new String[]{"Sept 1999", "Dec 1999","Dec 1999","Dec 1999","Dec 1999","Dec 1999","Jan 2000","Jun 2000","Aug 2000","Aug 2000","Oct 2000","Nov 2000","Dec 2000","Jan 2001","Mar 2001","Sept 2001","Sept 2001","Sept 2001","Oct 2001","Feb 2002","Mar 2002","Jun 2002","Jun 2002","Jun 2002","Jun 2002","Jul 2002","Dec 2002","Dec 2002","Dec 2002","Dec 2002","Jan 2003","Jan 2003","Jan 2003","Jan 2003","Feb 2003","May 2003","May 2003","Aug 2003","Sept 2003","Sept 2003","Dec 2003","Jan 2004","May 2004","Sept 2004"};

       GlobalClass globalClass=(GlobalClass) getApplicationContext();
       float r=globalClass.revenue;
       float p=globalClass.popularity;

       String reven=String.valueOf(r);
       String popula=String.valueOf(p);

       revenue.setText(reven+"MM");
       popu.setText(popula+"%");
       date.setText(datestring[globalClass.getEvent_no()]);
        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userId=user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile=snapshot.getValue(User.class);
                if(userProfile!=null)
                {
                    String name=userProfile.username;
                    int stimulus=userProfile.stimulus_spending;

                //    Log.i("TAG",name);
                  //  Log.i("TAG","Farmer Global="+String.valueOf(globalClass.getFarmer_sub()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, Environment.class));
            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, Education.class));
            }
        });

        foreign_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this,ForeignPolicy.class));
            }
        });

        healthcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, Healthcare.class));
            }
        });

        infrastructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, Infrastructure.class));
            }
        });

        safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, Safety.class));
            }
        });

        taxation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this,Taxation.class));
            }
        });

        welfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this,Welfare.class));
            }
        });
        economy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, Economy.class));
            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LandingPage.this,"You clicked on Text", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LandingPage.this,CurrentStatus.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(LandingPage.this,"You clicked on logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(LandingPage.this, MainActivity.class));
            }
        });

        lead_boa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this,Leaderboard.class));
            }
        });




    }

}