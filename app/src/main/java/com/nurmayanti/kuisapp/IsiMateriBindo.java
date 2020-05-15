package com.nurmayanti.kuisapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IsiMateriBindo extends AppCompatActivity {
private TextView materi1;
private FirebaseDatabase getDatabase;
private DatabaseReference getRefenence;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.isi_materi);
        getSupportActionBar().setTitle("Bahasa Indonesia");
        materi1 = findViewById(R.id.materibindo1);

final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("materi").child("bindo");

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        materi materi = dataSnapshot.getValue(materi.class);
        materi1.setText(materi.getMateri());
        }

@Override
public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.e("MyListData", "Error: ", databaseError.toException());
        }
        });
        }

        public void lanjutHal2(View view) {
        Intent i = new Intent(IsiMateriBindo.this,IsiMateriBindo2.class);
        startActivity(i);
        }
}