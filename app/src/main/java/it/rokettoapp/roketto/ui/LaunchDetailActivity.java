package it.rokettoapp.roketto.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterAstro;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.ui.viewmodel.AgencyViewModel;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.SpacecraftFlightViewModel;

public class LaunchDetailActivity extends AppCompatActivity {
    private AgencyViewModel mAgencyViewModel;
    private AstronautViewModel mAstroViewModel;
    private SpacecraftFlightViewModel mSpacecraftFlightModel;

    private List<Astronaut> mAstro;
    private Agency mAgency;
    private SpacecraftFlight mSpacecraft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);
        getSupportActionBar().hide();

        //Mi prendo i dati passati da Astronauta
        Launch mLaunch = (Launch) getIntent().getSerializableExtra("Launch");
        ImageView mThumbnail = (ImageView) findViewById(R.id.imageEvent);
        ImageView mImageAgency = (ImageView) findViewById(R.id.agencyLaunch);
        TextView mLaunchDescription = (TextView) findViewById(R.id.launchDetailDescription);
        mLaunchDescription.setText(mLaunch.getMission().getDescription());
        TextView mMinStage = (TextView) findViewById(R.id.min_stage_value);
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
        Log.d("idag", mLaunch.getLaunchServiceProvider().getId() + "");

        mAstro = new ArrayList<>();
        RecyclerView rvCrew = (RecyclerView) findViewById(R.id.rvCrew);

        LinearLayoutManager mLineaLayoutAstro = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCrew.setLayoutManager(mLineaLayoutAstro);

        RecyclerViewAdapterAstro mAdapterAstro = new RecyclerViewAdapterAstro(this, mAstro, false);
        rvCrew.setAdapter(mAdapterAstro);


        Chip mAgencyType = (Chip) findViewById(R.id.chipAgency);


        //Implementazione dei livedata
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        mAstroViewModel = viewModelProvider.get(AstronautViewModel.class);
        mAgencyViewModel = viewModelProvider.get(AgencyViewModel.class);
        mSpacecraftFlightModel = viewModelProvider.get(SpacecraftFlightViewModel.class);


        //Recupero dati agenzia dal lancio
        mAgencyViewModel.getLiveData().observe(this, agencies -> {
            mAgency = agencies.get(0);
            String mLogo = mAgency.getLogoUrl();



            if(mLogo != null)
            {
                mImageAgency.setVisibility(View.VISIBLE);
                Glide.with(this).load(mLogo).into(mImageAgency);
            }

            mLaunchAgencyName.setText(mAgency.getName());
            mAgencyType.setText(mAgency.getType());

            mLaunchAgencyDescription.setText(mAgency.getDescription());
        });
        //Chiamata alla repository
        mAgencyViewModel.getAgencyById(mLaunch.getLaunchServiceProvider().getId());


        //Recupero dati crew dal lancio
        mSpacecraftFlightModel.getLiveData().observe(this, spacecraftFlights -> {
            mSpacecraft = spacecraftFlights.get(0);
            Log.d("deb" , mSpacecraft.getLaunchCrew().get(0).getAstronaut().getId() + "");

            List<Integer> idsAstro = new ArrayList<>();

            for(AstronautFlight a : mSpacecraft.getLaunchCrew())
                idsAstro.add(a.getAstronaut().getId());
            mAstroViewModel.getAstronautsByIds(idsAstro);
        });

        mAstroViewModel.getLiveData().observe(this, astronauts -> {
            mAstro.addAll(astronauts);
            mAdapterAstro.notifyDataSetChanged();
        });
        mSpacecraftFlightModel.getSpacecraftFlightById(mLaunch.getRocket().getSpacecraftStage().getId());



        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backButtonLaunch);
        back.setOnClickListener(v -> onBackPressed());

    }
}