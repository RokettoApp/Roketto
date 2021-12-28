package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterAstro;
import it.rokettoapp.roketto.databinding.ActivityLaunchDetailBinding;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.ui.viewmodel.LaunchViewModel;

public class LaunchDetailActivity extends AppCompatActivity {

    private List<Astronaut> mAstro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);
        getSupportActionBar().hide();

        ActivityLaunchDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_launch_detail);

        mAstro = new ArrayList<>();


        //Mi prendo i dati passati da Astronauta
        Launch mLaunch = (Launch) getIntent().getSerializableExtra("LaunchId");
        binding.setLaunch(mLaunch);
        ImageView mThumbnail = (ImageView) findViewById(R.id.imageEvent);
        ImageView mImageAgency = (ImageView) findViewById(R.id.agencyLaunch);
        /*TextView mMinStage = (TextView) findViewById(R.id.min_stage_value);
        mMinStage.setText(mLaunch.getRocket().getConfiguration().getMinStage() == 0 ? "--" : mLaunch.getRocket().getConfiguration().getMinStage()+"");
        TextView mMaxStage = (TextView) findViewById(R.id.max_stage_value);
        mMaxStage.setText(mLaunch.getRocket().getConfiguration().getMaxStage() == 0 ? "--" : mLaunch.getRocket().getConfiguration().getMaxStage()+"");
        TextView mLength = (TextView) findViewById(R.id.length_value);
        mLength.setText(mLaunch.getRocket().getConfiguration().getLength() == 0 ? "--" : mLaunch.getRocket().getConfiguration().getLength()+"");
        TextView mDiameter = (TextView) findViewById(R.id.diameter_value);
        mDiameter.setText(mLaunch.getRocket().getConfiguration().getDiameter() == 0 ? "--" : mLaunch.getRocket().getConfiguration().getDiameter()+"");
        TextView mLaunchMass = (TextView) findViewById(R.id.launch_mass_value);
        mLaunchMass.setText(mLaunch.getRocket().getConfiguration().getLaunchMass() == 0 ? "--" :mLaunch.getRocket().getConfiguration().getLaunchMass()+"" );
        TextView mThrust = (TextView) findViewById(R.id.thrust_value);
        mThrust.setText(mLaunch.getRocket().getConfiguration().getToThrust() == 0 ? "--" : mLaunch.getRocket().getConfiguration().getToThrust()+"" );
        TextView mName = (TextView) findViewById(R.id.name_value);
        mName.setText(mLaunch.getRocket().getConfiguration().getName() == "" ? "--" : mLaunch.getRocket().getConfiguration().getName());
        TextView mFamily = (TextView) findViewById(R.id.family_value);
        mFamily.setText(mLaunch.getRocket().getConfiguration().getFamily() == "" ? "--" : mLaunch.getRocket().getConfiguration().getFamily() );
        TextView mVariant = (TextView) findViewById(R.id.variant_value);
        mVariant.setText(mLaunch.getRocket().getConfiguration().getVariant() == "" ? "--" :mLaunch.getRocket().getConfiguration().getVariant());
        TextView mAlias = (TextView) findViewById(R.id.alias_value);
        mAlias.setText(mLaunch.getRocket().getConfiguration().getAlias() == "" ? "--" :mLaunch.getRocket().getConfiguration().getAlias() );
        TextView mFullName = (TextView) findViewById(R.id.full_name_value);
        mFullName.setText(mLaunch.getRocket().getConfiguration().getFullName() == "" ? "--" :mLaunch.getRocket().getConfiguration().getFullName());
        TextView mLaunchCost = (TextView) findViewById(R.id.launch_cost_value);
        mLaunchCost.setText(mLaunch.getRocket().getConfiguration().getLaunchCost()  == "" ? "--" :mLaunch.getRocket().getConfiguration().getLaunchCost());
        TextView mLowOrbit = (TextView) findViewById(R.id.low_orbit_value);
        mLowOrbit.setText(mLaunch.getRocket().getConfiguration().getLeoCapacity()  == 0 ? "--" : mLaunch.getRocket().getConfiguration().getLeoCapacity()+"");
        TextView mGeoTra = (TextView) findViewById(R.id.geo_tra_value);
        mGeoTra.setText(mLaunch.getRocket().getConfiguration().getGtoCapacity()  == 0 ? "--" :mLaunch.getRocket().getConfiguration().getGtoCapacity() +"");
        TextView mLaunchAgencyName = (TextView) findViewById(R.id.launchAgencyName);
        TextView mLaunchAgencyDescription = (TextView) findViewById(R.id.agencyLaunchDescr);
        mLaunchAgencyName.setText(mLaunch.getLaunchServiceProvider().getName());
        Log.d("nameag", mLaunch.getLaunchServiceProvider().getName() + "");
        Log.d("idag", mLaunch.getLaunchServiceProvider().getId() + "");*/

        String mLogo = mLaunch.getLaunchServiceProvider().getLogoUrl();
        if(mLogo != null)
        {
            mImageAgency.setVisibility(View.VISIBLE);
            Glide.with(this).load(mLogo).into(mImageAgency);
        }

        binding.setAgency(mLaunch.getLaunchServiceProvider());
        /*Chip mAgencyType = (Chip) findViewById(R.id.chipAgency);
        mLaunchAgencyName.setText(mAgency.getName());
        mAgencyType.setText(mAgency.getType());
        mLaunchAgencyDescription.setText(mAgency.getDescription());*/

        if (mLaunch.getRocket().getSpacecraftStage() != null) {
            for (AstronautFlight astronautFlight :
                    mLaunch.getRocket().getSpacecraftStage().getLaunchCrew()) {
                mAstro.add(astronautFlight.getAstronaut());
            }
        }

        RecyclerView rvCrew = (RecyclerView) findViewById(R.id.rvCrew);

        LinearLayoutManager mLineaLayoutAstro = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCrew.setLayoutManager(mLineaLayoutAstro);

        RecyclerViewAdapterAstro mAdapterAstro = new RecyclerViewAdapterAstro(this, mAstro, false);
        rvCrew.setAdapter(mAdapterAstro);

        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backButtonLaunch);
        back.setOnClickListener(v -> onBackPressed());
    }
}