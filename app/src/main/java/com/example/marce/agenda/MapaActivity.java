package com.example.marce.agenda;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.SupportMapFragment;

public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = supportFragmentManager.beginTransaction();
        tx.replace(R.id.frame_mapa, new MapaFragment());
        tx.commit();
    }
}
