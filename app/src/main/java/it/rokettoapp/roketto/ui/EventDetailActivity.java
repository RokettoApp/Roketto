package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.DockingLocation;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.DockingEventViewModel;
import it.rokettoapp.roketto.ui.viewmodel.ExpeditionViewModel;
import it.rokettoapp.roketto.ui.viewmodel.LaunchViewModel;
import it.rokettoapp.roketto.ui.viewmodel.ProgramViewModel;
import it.rokettoapp.roketto.ui.viewmodel.SpaceStationViewModel;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Event mEvent = (Event) getIntent().getSerializableExtra("Event");

        TextView eventDetailName = findViewById(R.id.eventDetail_name);
        eventDetailName.setText(mEvent.getName());

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        LaunchViewModel launchViewModel = viewModelProvider.get(LaunchViewModel.class);
        ProgramViewModel programViewModel = viewModelProvider.get(ProgramViewModel.class);
        ExpeditionViewModel expeditionViewModel = viewModelProvider.get(ExpeditionViewModel.class);
        AstronautViewModel astronautViewModel = viewModelProvider.get(AstronautViewModel.class);
        SpaceStationViewModel spaceStationViewModel =
                viewModelProvider.get(SpaceStationViewModel.class);
        DockingEventViewModel dockingEventViewModel =
                viewModelProvider.get(DockingEventViewModel.class);

        TextView textView8 = findViewById(R.id.textView8);
        launchViewModel.getLiveData().observe(this, launchList -> {

            Launch launch = launchList.get(0);
            textView8.append(launch.getName() + "\n");
            for (Program program : launch.getProgramList()) {
                programViewModel.getProgramById(program.getId());
            }
        });

        TextView textView9 = findViewById(R.id.textView9);
        programViewModel.getLiveData().observe(this, programList ->
                textView9.append(programList.get(0).getName() + "\n"));

        TextView textView10 = findViewById(R.id.textView10);
        expeditionViewModel.getLiveData().observe(this, expeditionList -> {

            Expedition expedition = expeditionList.get(0);
            textView10.append(expedition.getName() + "\n");
            for (AstronautFlight astronautFlight : expedition.getCrew()) {
                astronautViewModel.getAstronautById(astronautFlight.getmAstronaut().getId());
            }

        });

        TextView textView11 = findViewById(R.id.textView11);
        astronautViewModel.getLiveData().observe(this, astronautList ->
                textView11.append(astronautList.get(0).getName() + "\n"));

        TextView textView12 = findViewById(R.id.textView12);
        spaceStationViewModel.getLiveData().observe(this, spaceStationList -> {

            SpaceStation spaceStation = spaceStationList.get(0);
            textView12.append(spaceStation.getName() + "\n");
            for (DockingLocation dockingLocation : spaceStation.getDockingLocationList()) {
                if (dockingLocation.getDockingEvent() != null) {
                    dockingEventViewModel
                            .getDockingEventById(dockingLocation.getDockingEvent().getId());
                }
            }
        });

        TextView textView13 = findViewById(R.id.textView13);
        dockingEventViewModel.getLiveData().observe(this, dockingEventList ->
                textView13.append(dockingEventList.get(0).getDocking().toString() + "\n"));

        for (Launch launch : mEvent.getLaunchList()) {
            launchViewModel.getLaunchById(launch.getId());
        }
        for (Expedition expedition : mEvent.getExpeditionList()) {
            expeditionViewModel.getExpeditionById(expedition.getId());
        }
        for (SpaceStation spaceStation : mEvent.getSpaceStationList()) {
            spaceStationViewModel.getSpaceStationById(spaceStation.getId());
        }
    }
}