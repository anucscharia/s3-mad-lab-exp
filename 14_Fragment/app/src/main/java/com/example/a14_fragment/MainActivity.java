package com.example.a14_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showText(String topImageText, String bottomImageText) {
        bottomfragment bottomFragment = (bottomfragment) this.getSupportFragmentManager().findFragmentById(R.id.fragment_bottom);
        bottomFragment.showText(topImageText, bottomImageText);
    }
}