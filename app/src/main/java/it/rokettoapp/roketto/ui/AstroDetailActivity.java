package it.rokettoapp.roketto.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Astronaut;

public class AstroDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_detail);

        Astronaut mAstro = (Astronaut) getIntent().getSerializableExtra("Astronaut");

        ((TextView)findViewById(R.id.astroDetail_name)).setText(mAstro.getName());
    }
}