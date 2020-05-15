package com.nurmayanti.kuisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MateriSaintek extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materi_saintek);

    }

    public void onClickBindo(View view) {
        Intent intent = new Intent(MateriSaintek.this,IsiMateriBindo.class);
        startActivity(intent);
    }

    public void onClickBinggris(View view) {
        Intent intent = new Intent(MateriSaintek.this,IsiMateriBingg.class);
        startActivity(intent);
    }

    public void onClickMatdas(View view) {
        Intent intent = new Intent(MateriSaintek.this,IsiMateriMatdas.class);
        startActivity(intent);
    }

    public void onClickKimia(View view) {
        Intent intent = new Intent(MateriSaintek.this,IsiMateriKimia.class);
        startActivity(intent);
    }

    public void onClickFisika(View view) {
        Intent intent = new Intent(MateriSaintek.this,IsiMateriFis.class);
        startActivity(intent);
    }

    public void onClickBiologi(View view) {
        Intent intent = new Intent(MateriSaintek.this,IsiMateriBio.class);
        startActivity(intent);
    }
}
