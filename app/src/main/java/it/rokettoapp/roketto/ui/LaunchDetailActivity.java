package it.rokettoapp.roketto.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.Launch;

public class LaunchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);
        getSupportActionBar().hide();

        //Mi prendo i dati passati da Astronauta
        Launch mLaunch = (Launch) getIntent().getSerializableExtra("Launch");
        ImageView mThumbnail = (ImageView) findViewById(R.id.imageEvent);

        TextView mLaunchDescription = (TextView) findViewById(R.id.launchDetailDescription);
        TextView mMinStage = (TextView) findViewById(R.id.min_stage_value);
        TextView mMaxStage = (TextView) findViewById(R.id.max_stage_value);
        TextView mLength = (TextView) findViewById(R.id.length_value);
        TextView mDiameter = (TextView) findViewById(R.id.diameter_value);
        TextView mFairingDiameter = (TextView) findViewById(R.id.fairing_diameter_value);
        TextView mLaunchMass = (TextView) findViewById(R.id.launch_mass_value);
        TextView mThrust = (TextView) findViewById(R.id.thrust_value);
        TextView mName = (TextView) findViewById(R.id.name_value);
        TextView mFamily = (TextView) findViewById(R.id.family_value);
        TextView mVariant = (TextView) findViewById(R.id.variant_value);
        TextView mAlias = (TextView) findViewById(R.id.alias_value);
        TextView mFullName = (TextView) findViewById(R.id.full_name_value);
        TextView mLaunchCost = (TextView) findViewById(R.id.launch_cost_value);
        TextView mLowOrbit = (TextView) findViewById(R.id.low_orbit_value);
        TextView mGeoTra = (TextView) findViewById(R.id.geo_tra_value);
        TextView mDirectGeo = (TextView) findViewById(R.id.direct_geo_value);
        TextView mSunSync = (TextView) findViewById(R.id.sun_sync_value);

        RecyclerView rvCrew = (RecyclerView) findViewById(R.id.rvCrew);

        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backButtonLaunch);
        back.setOnClickListener(v -> onBackPressed());

    }
}