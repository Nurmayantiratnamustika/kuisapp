package com.nurmayanti.kuisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MateriSoshum extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materi_soshum);

    }

    public void onClickBindoSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriBindo.class);
        startActivity(intent);
    }

    public void onClickBinggSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriBingg.class);
        startActivity(intent);
    }

    public void onClickMatdasSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriMatdas.class);
        startActivity(intent);
    }

    public void onClickEkoSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriEko.class);
        startActivity(intent);
    }

    public void onClickSejarahSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriSejarah.class);
        startActivity(intent);
    }

    public void onClickSosiologiSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriSosiologi.class);
        startActivity(intent);
    }

    public void onClickGeografiSos(View view) {
        Intent intent = new Intent(MateriSoshum.this,IsiMateriGeo.class);
        startActivity(intent);
    }
}
