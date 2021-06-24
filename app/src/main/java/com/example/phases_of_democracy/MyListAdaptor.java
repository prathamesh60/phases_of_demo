package com.example.phases_of_democracy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyListAdaptor extends ArrayAdapter<score> {
    List<score> scoreList;
    Context context;
    int resource;

    public MyListAdaptor(Context context, int resource, List<score> scoreList){
        super(context, resource, scoreList);
        this.context=context;
        this.resource=resource;
        this.scoreList=scoreList;


    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.my_custom_list,null);

        TextView textView=view.findViewById(R.id.player);
        TextView textView1=view.findViewById(R.id.score);

        final score obj=scoreList.get(position);

        textView.setText(String.valueOf(position+1)+") "+obj.getPlayer());
        textView1.setText(String.valueOf(obj.getScore()));

        return view;
    }



}
