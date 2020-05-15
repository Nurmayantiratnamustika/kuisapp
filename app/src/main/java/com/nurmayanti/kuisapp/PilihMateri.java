package com.nurmayanti.kuisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class PilihMateri extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_materi);

    }

    public void onClickSaintek(View view) {
        Intent intent = new Intent(PilihMateri.this,MateriSaintek.class);
        startActivity(intent);
    }

    public void onClickSoshum(View view) {
        Intent intent = new Intent(PilihMateri.this,MateriSoshum.class);
        startActivity(intent);
    }
}
