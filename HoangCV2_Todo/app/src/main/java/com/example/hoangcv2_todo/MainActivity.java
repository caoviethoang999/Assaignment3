package com.example.hoangcv2_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListNoteFragment recylerFragment = new ListNoteFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
    }
}