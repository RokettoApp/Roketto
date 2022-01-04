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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jwang123.flagkit.FlagKit;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterLaunches;
import it.rokettoapp.roketto.databinding.ActivityAstroDetailBinding;
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

        ActivityAstroDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_astro_detail);

        Log.d("logastrodeital", "Acitivity astronauta");
        //Inizializzazione variabili
        int mAstroId = (int) getIntent().getSerializableExtra("Astronaut");
        TextView mAstroBirth = (TextView) findViewById(R.id.txtBirthDate);
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

        RecyclerViewAdapterLaunches mAdapterLaunches = new RecyclerViewAdapterLaunches(this, mAstroLaunches, false);
        mRecyclerLaunches.setAdapter(mAdapterLaunches);

        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backButtonAstro);
        back.setOnClickListener(v -> onBackPressed());


        //Recupero dati astronauta
        mAstroViewModel.getLiveData().observe(this, astronauts -> {
            mAstro = astronauts.getResults().get(0);
            binding.setAstro(mAstro);
            String urlWiki = mAstro.getWikipedia();
            mWiki.setOnClickListener(v -> clicke_btn(urlWiki));
            String urlInsta = mAstro.getInstagram();
            mInsta.setOnClickListener(v -> clicke_btn(urlInsta) );
            String urlTwitter = mAstro.getTwitter();
            mTwitter.setOnClickListener(v -> clicke_btn(urlTwitter));


            Glide.with(this).load(mAstro.getProfileImage()).into(mAstroProfile);

            if(mCSVCountries.checkCountryCode(mAstro.getNationality()))
                mFlag.setImageDrawable(FlagKit.drawableWithFlag(this, mCSVCountries.getCodeFromName(mAstro.getNationality()).toLowerCase()));

            String[] date = mAstro.getDateOfBirth().toString().split("\\s+");
            binding.setDate(date[2] + "/"+ date[1]+ "/"+ date[5]);

            if(mAstro.getAgency() != null)
                mAgencyViewModel.getAgencyById(mAstro.getAgency().getId());

            List<String> idsLaunches = new ArrayList<>();
            for (Launch l: mAstro.getFlightList())
                idsLaunches.add(l.getId());

            mLaunchViewModel.getLaunchesByIds(idsLaunches);

        });
        mAstroViewModel.getAstronautById(mAstroId);

        //Recupero dati agenzia dell'astronauta
        mAgencyViewModel.getLiveData().observe(this, response -> {

            if (!response.isError()) {
                mAgency = response.getResults().get(0);
                binding.setAgency(mAgency);
                String mLogo = mAgency.getLogoUrl();

                if(mLogo != null) {
                    mAgencyAstro.setVisibility(View.VISIBLE);
                    Glide.with(this).load(mLogo).into(mAgencyAstro);
                }
            } else {
                showError(response.getMessage());
            }
        });

        //Recupero dati lanci dell'astronauta
        mLaunchViewModel.getLiveData().observe(this, response -> {

            if (!response.isError()) {
                mAstroLaunches.addAll(response.getResults());
                Log.d("AstroDetailActivity" , "Observer aggiornato " + mAstroLaunches.size());
                mAdapterLaunches.notifyDataSetChanged();
            } else {
                showError(response.getMessage());
            }
        });
    }

    public void clicke_btn(String url){
        if(url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

    private void showError(String errorMessage) {

        Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}