package it.rokettoapp.roketto.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import it.rokettoapp.roketto.R;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide();
    }
}