package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.ui.viewmodel.AgencyViewModel;

public class AstroDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_detail);

        Astronaut mAstro = (Astronaut) getIntent().getSerializableExtra("Astronaut");

        ((TextView)findViewById(R.id.astroDetail_name)).setText(mAstro.getName());

        AgencyViewModel mAgencyViewModel = new ViewModelProvider(this)
                .get(AgencyViewModel.class);
        TextView textView7 = findViewById(R.id.textView7);
        mAgencyViewModel.getAgencyById(mAstro.getAgency().getId()).observe(this, agencyList -> {

            textView7.setText(agencyList.get(0).getName());
            Log.d("AgencyObserver", "test");
        });
    }
}