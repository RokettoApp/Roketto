package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterAstro;
import it.rokettoapp.roketto.databinding.ActivityLaunchDetailBinding;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.Launch;
import jp.wasabeef.glide.transformations.BlurTransformation;

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
        binding.setDefaultString("--");
        binding.setEmptyString("");

        ImageView mThumbnail = (ImageView) findViewById(R.id.imageEvent);
        ImageView mImageAgency = (ImageView) findViewById(R.id.agencyLaunch);
        TextView mDay = (TextView) findViewById(R.id.txtGiorno);
        TextView mMonth = (TextView) findViewById(R.id.txtMese);
        TextView mYear = (TextView) findViewById(R.id.txtAnno);

        String[] dateLaunch = mLaunch.getNet().toString().split("\\s+");
        mDay.setText(dateLaunch[2]);
        mMonth.setText(dateLaunch[1]);
        mYear.setText(dateLaunch[5]);

        Glide.with(this).load(mLaunch.getImage()).apply(RequestOptions.bitmapTransform(
                new BlurTransformation(15))).into(mThumbnail);


        String mLogo = mLaunch.getLaunchServiceProvider().getLogoUrl();
        if(mLogo != null) {
            mImageAgency.setVisibility(View.VISIBLE);
            Glide.with(this).load(mLogo).into(mImageAgency);
        }

        binding.setAgency(mLaunch.getLaunchServiceProvider());

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