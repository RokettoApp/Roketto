package it.rokettoapp.roketto.ui;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterEvents;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterLaunches;
import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.ui.viewmodel.AgencyViewModel;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.LaunchViewModel;

public class AstroDetailActivity extends AppCompatActivity {

    private AstronautViewModel mAstroViewModel;
    private AgencyViewModel mAgencyViewModel;
    private LaunchViewModel mLaunchViewModel;
    private Astronaut mAstro;
    private Agency mAgency;
    private List<Launch> mAstroLaunches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_detail);
        getSupportActionBar().hide();

        //Inizializzazione variabili
        int mAstroId = (int) getIntent().getSerializableExtra("Astronaut");
        TextView mAstroDescription = (TextView) findViewById(R.id.astroDetailDescription);
        TextView mAgencyDescription = (TextView) findViewById(R.id.agencyAstroDescr);
        ImageView mAstroProfile = (ImageView) findViewById(R.id.imageAstro);
        ImageView mAgencyAstro = (ImageView) findViewById(R.id.agencyAstro);
        Button mLaunchesSeeAll = (Button) findViewById(R.id.launches_see_all);

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

        RecyclerViewAdapterLaunches mAdapterLaunches = new RecyclerViewAdapterLaunches(this, mAstroLaunches, true);
        mRecyclerLaunches.setAdapter(mAdapterLaunches);

        //Implementazione backbutton
        ImageButton back = (ImageButton)findViewById(R.id.backButtonAstro);
        back.setOnClickListener(v -> onBackPressed());

        //Recupero dati astronauta
        mAstroViewModel.getLiveData().observe(this, astronauts -> {
            mAstro = astronauts.get(0);

            Log.d("FragmentHome", "dentro");

            Glide.with(this).load(mAstro.getProfileImage()).into(mAstroProfile);
            mAstroDescription.setText(mAstro.getBiography());

            mAgencyViewModel.getAgencyById(mAstro.getAgency().getId());

            if(mAstro.getFlightList().size() > 2)
                mLaunchesSeeAll.setVisibility(View.VISIBLE);

            for (Launch l: mAstro.getFlightList()) {
                mLaunchViewModel.getLaunchById(l.getId());

            }
        });
        mAstroViewModel.getAstronautById(mAstroId);

        //Recupero dati agenzia dell'astronauta
        mAgencyViewModel.getLiveData().observe(this, agencies -> {
            mAgency = agencies.get(0);
            String mLogo = mAgency.getLogoUrl();

            Log.d("FragmentHome", "dentro b");

            if(mLogo != null)
            {
                mAgencyAstro.setVisibility(View.VISIBLE);
                Glide.with(this).load(mLogo).into(mAgencyAstro);
            }

            mAgencyDescription.setText(mAgency.getDescription());
        });


        //Recupero dati lanci dell'astronauta
        mLaunchViewModel.getLiveData().observe(this, launches -> {
            mAstroLaunches.addAll(launches);
            mAdapterLaunches.notifyDataSetChanged();
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
}