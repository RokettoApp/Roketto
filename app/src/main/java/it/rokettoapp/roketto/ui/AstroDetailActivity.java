package it.rokettoapp.roketto.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.jwang123.flagkit.FlagKit;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterLaunches;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.ui.viewmodel.AgencyViewModel;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.LaunchViewModel;
import it.rokettoapp.roketto.util.CSVCountries;

public class AstroDetailActivity extends AppCompatActivity {

    private AstronautViewModel mAstroViewModel;
    private AgencyViewModel mAgencyViewModel;
    private LaunchViewModel mLaunchViewModel;
    private Astronaut mAstro;
    private Agency mAgency;
    private List<Launch> mAstroLaunches;
    private CSVCountries mCSVCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_detail);
        getSupportActionBar().hide();

        Log.d("logastrodeital", "Acitivity astronauta");
        //Inizializzazione variabili
        int mAstroId = (int) getIntent().getSerializableExtra("Astronaut");
        TextView mAstroDescription = (TextView) findViewById(R.id.astroDetailDescription);
        TextView mAgencyDescription = (TextView) findViewById(R.id.agencyAstroDescr);
        TextView mAgencyName = (TextView) findViewById(R.id.astroAgencyName);
        TextView mNationality = (TextView) findViewById(R.id.txtNationality);
        TextView mAstroStatus = (TextView) findViewById(R.id.txtStatus);
        TextView mAstroName = (TextView) findViewById(R.id.astronautsNameTitle);
        TextView mAstroBirth = (TextView) findViewById(R.id.txtBirthDate);
        Chip mAgencyType = (Chip) findViewById(R.id.chipAgency);
        ImageView mAstroProfile = (ImageView) findViewById(R.id.imageAstro);
        ImageView mAgencyAstro = (ImageView) findViewById(R.id.agencyAstro);
        ImageView mWiki = (ImageView) findViewById(R.id.iconWiki);
        ImageView mInsta = (ImageView) findViewById(R.id.iconInsta);
        ImageView mTwitter = (ImageView) findViewById(R.id.iconTwitter);
        ImageView mFlag = (ImageView) findViewById(R.id.iconFlag);
        Button mLaunchesSeeAll = (Button) findViewById(R.id.launches_see_all);
        mCSVCountries = CSVCountries.getInstanceCountry();



        if(mAstroLaunches == null)
            mAstroLaunches = new ArrayList<>();



        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        mAstroViewModel = viewModelProvider.get(AstronautViewModel.class);
        mAgencyViewModel = viewModelProvider.get(AgencyViewModel.class);
        mLaunchViewModel = viewModelProvider.get(LaunchViewModel.class);


        //Inizializzazione RecyclerView
        RecyclerView mRecyclerLaunches = (RecyclerView) findViewById(R.id.launchesAstrorv);
        LinearLayoutManager mLineaLayoutLaunches = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerLaunches.setLayoutManager(mLineaLayoutLaunches);


        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backButtonAstro);
        back.setOnClickListener(v -> onBackPressed());


        //Recupero dati lanci dell'astronauta

        //Recupero dati astronauta
        mAstroViewModel.getLiveData().observe(this, astronauts -> {
            mAstro = astronauts.get(0);
            String urlWiki = mAstro.getWikipedia();
            setVisibilityListener(mWiki, urlWiki);
            String urlInsta = mAstro.getInstagram();
            setVisibilityListener(mInsta, urlInsta);
            String urlTwitter = mAstro.getTwitter();
            setVisibilityListener(mTwitter, urlTwitter);


            Glide.with(this).load(mAstro.getProfileImage()).into(mAstroProfile);
            mAstroDescription.setText(mAstro.getBiography());
            mNationality.setText(mAstro.getNationality());
            mAstroStatus.setText(mAstro.getStatus().getName());
            mAstroName.setText(mAstro.getName());

            if(mCSVCountries.checkCountryCode(mAstro.getNationality()))
                mFlag.setImageDrawable(FlagKit.drawableWithFlag(this, mCSVCountries.getCodeFromName(mAstro.getNationality()).toLowerCase()));

            String[] date = mAstro.getDateOfBirth().toString().split("\\s+");
            mAstroBirth.setText(date[2] + "/"+ date[1]+ "/"+ date[5]);

            if(mAstro.getAgency() != null)
                mAgencyViewModel.getAgencyById(mAstro.getAgency().getId());

            if(mAstro.getFlightList().size() > 5)
                mLaunchesSeeAll.setVisibility(View.VISIBLE);


            RecyclerViewAdapterLaunches mAdapterLaunches = new RecyclerViewAdapterLaunches(this, mAstroLaunches, false);
            mRecyclerLaunches.setAdapter(mAdapterLaunches);
            mLaunchViewModel.getLiveData().observe(this, launches -> {
                mAstroLaunches.addAll(launches);
                Log.d("aggobs" , "Observer aggiornato " + mAstroLaunches.size());
                mAdapterLaunches.notifyDataSetChanged();

            });

            List<String> idsLaunches = new ArrayList<>();
            for (Launch l: mAstro.getFlightList())
                idsLaunches.add(l.getId());
            mLaunchViewModel.getLaunchesByIds(idsLaunches);
        });
        mAstroViewModel.getAstronautById(mAstroId);

        //Recupero dati agenzia dell'astronauta
        mAgencyViewModel.getLiveData().observe(this, agencies -> {
            mAgency = agencies.get(0);
            String mLogo = mAgency.getLogoUrl();



            if(mLogo != null)
            {
                mAgencyAstro.setVisibility(View.VISIBLE);
                Glide.with(this).load(mLogo).into(mAgencyAstro);
            }

            mAgencyName.setText(mAgency.getName());
            mAgencyType.setText(mAgency.getType());

            mAgencyDescription.setText(mAgency.getDescription());
        });





        /*((TextView)findViewById(R.id.astroDetail_name)).setText(mAstro.getName());

        AgencyViewModel mAgencyViewModel = new ViewModelProvider(this)
                .get(AgencyViewModel.class);
        TextView textView7 = findViewById(R.id.textView7);
        mAgencyViewModel.getLiveData().observe(this, agencyList -> {

            textView7.setText(agencyList.get(0).getName());
            Log.d("AgencyObserver", "test");
        });
        mAgencyViewModel.getAgencyById(mAstro.getAgency().getId());*/
    }

    public void clicke_btn(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void setVisibilityListener (ImageView icon, String url){
        if(url != null){
            icon.setVisibility(View.VISIBLE);
            icon.setOnClickListener(v -> clicke_btn(url));
        }
    }
}