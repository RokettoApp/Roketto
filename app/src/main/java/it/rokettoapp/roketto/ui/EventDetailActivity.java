package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.adapter.ProgramRecyclerViewAdapter;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterAstro;
import it.rokettoapp.roketto.adapter.RecyclerViewAdapterLaunches;
import it.rokettoapp.roketto.adapter.SpaceStationRecyclerViewAdapter;
import it.rokettoapp.roketto.databinding.ActivityEventDetailBinding;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.ui.viewmodel.EventViewModel;
import it.rokettoapp.roketto.ui.viewmodel.ExpeditionViewModel;

public class EventDetailActivity extends AppCompatActivity {

    private ActivityEventDetailBinding binding;
    private List<Launch> mLaunchList;
    private List<SpaceStation> mSpaceStationList;
    private List<Astronaut> mAstronautList;
    private List<Program> mProgramList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        int eventId = (int) getIntent().getSerializableExtra("EventId");

        EventViewModel eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getLiveData().observe(this, eventList -> {

            Event mEvent = eventList.get(0);

            mLaunchList = mEvent.getLaunchList();
            mSpaceStationList = mEvent.getSpaceStationList();
            mAstronautList = new ArrayList<>();
            mProgramList = mEvent.getProgramList();

            Glide.with(this).load(mEvent.getFeatureImage()).into(binding.imageEvent);
            binding.backButtonEvent.setOnClickListener(v -> onBackPressed());
            binding.eventDetailDescription.setText(mEvent.getDescription());

            if (mEvent.getLaunchList().size() > 0) {
                RecyclerView launchRecyclerView = (RecyclerView)
                        findViewById(R.id.recyclerViewLaunch);
                LinearLayoutManager launchlinearLayoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
                launchRecyclerView.setLayoutManager(launchlinearLayoutManager);
                RecyclerViewAdapterLaunches recyclerViewAdapterLaunches =
                        new RecyclerViewAdapterLaunches(this, mLaunchList, false);
                launchRecyclerView.setAdapter(recyclerViewAdapterLaunches);

                findViewById(R.id.launchTitle).setVisibility(View.VISIBLE);
                findViewById(R.id.recyclerViewLaunch).setVisibility(View.VISIBLE);
            }

            if (mEvent.getSpaceStationList().size() > 0) {
                RecyclerView spaceStationRecyclerView = (RecyclerView)
                        findViewById(R.id.recyclerViewSpaceStation);
                LinearLayoutManager spaceStationlinearLayoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
                spaceStationRecyclerView.setLayoutManager(spaceStationlinearLayoutManager);
                SpaceStationRecyclerViewAdapter spaceStationRecyclerViewAdapter =
                        new SpaceStationRecyclerViewAdapter(this, mSpaceStationList);
                spaceStationRecyclerView.setAdapter(spaceStationRecyclerViewAdapter);

                findViewById(R.id.stationTitle).setVisibility(View.VISIBLE);
                findViewById(R.id.recyclerViewSpaceStation).setVisibility(View.VISIBLE);
            }

            if (mEvent.getExpeditionList().size() > 0) {
                RecyclerView expeditionRecyclerView = (RecyclerView)
                        findViewById(R.id.recyclerViewExpedition);
                LinearLayoutManager expeditionLinearLayoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL, false);
                expeditionRecyclerView.setLayoutManager(expeditionLinearLayoutManager);
                RecyclerViewAdapterAstro astronautRecyclerViewAdapter =
                        new RecyclerViewAdapterAstro(this, mAstronautList, false);
                expeditionRecyclerView.setAdapter(astronautRecyclerViewAdapter);

                findViewById(R.id.expeditionTitle).setVisibility(View.VISIBLE);
                findViewById(R.id.recyclerViewExpedition).setVisibility(View.VISIBLE);

                ExpeditionViewModel expeditionViewModel =
                        new ViewModelProvider(this).get(ExpeditionViewModel.class);

                expeditionViewModel.getLiveData().observe(this, expeditionList -> {

                    Expedition expedition = expeditionList.get(0);

                    for (AstronautFlight astronautFlight : expedition.getCrew()) {
                        mAstronautList.add(astronautFlight.getAstronaut());
                        astronautRecyclerViewAdapter.notifyItemInserted(mAstronautList.size() - 1);
                    }
                });

                for (Expedition expedition : mEvent.getExpeditionList()) {
                    expeditionViewModel.getExpeditionById(expedition.getId());
                }
            }

            if (mEvent.getProgramList().size() > 0) {
                RecyclerView programRecyclerView = (RecyclerView)  findViewById(R.id.recyclerViewProgram);
                LinearLayoutManager programLinearLayoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
                programRecyclerView.setLayoutManager(programLinearLayoutManager);
                ProgramRecyclerViewAdapter programRecyclerViewAdapter =
                        new ProgramRecyclerViewAdapter(this, mProgramList);
                programRecyclerView.setAdapter(programRecyclerViewAdapter);

                findViewById(R.id.programTitle).setVisibility(View.VISIBLE);
                findViewById(R.id.recyclerViewProgram).setVisibility(View.VISIBLE);
            }
        });
        eventViewModel.getEventById(eventId);
    }
}