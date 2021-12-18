package it.rokettoapp.roketto.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.model.AstronautFlight;
import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.DockingLocation;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.model.SpacecraftFlight;
import it.rokettoapp.roketto.ui.viewmodel.AstronautViewModel;
import it.rokettoapp.roketto.ui.viewmodel.DockingEventViewModel;
import it.rokettoapp.roketto.ui.viewmodel.ExpeditionViewModel;
import it.rokettoapp.roketto.ui.viewmodel.LaunchViewModel;
import it.rokettoapp.roketto.ui.viewmodel.LocationViewModel;
import it.rokettoapp.roketto.ui.viewmodel.PadViewModel;
import it.rokettoapp.roketto.ui.viewmodel.ProgramViewModel;
import it.rokettoapp.roketto.ui.viewmodel.SpaceStationViewModel;
import it.rokettoapp.roketto.ui.viewmodel.SpacecraftFlightViewModel;
import it.rokettoapp.roketto.ui.viewmodel.SpacecraftViewModel;

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
        PadViewModel padViewModel = viewModelProvider.get(PadViewModel.class);
        LocationViewModel locationViewModel = viewModelProvider.get(LocationViewModel.class);
        SpacecraftFlightViewModel spacecraftFlightViewModel =
                viewModelProvider.get(SpacecraftFlightViewModel.class);
        SpacecraftViewModel spacecraftViewModel = viewModelProvider.get(SpacecraftViewModel.class);

        TextView textView8 = findViewById(R.id.textView8);
        launchViewModel.getLiveData().observe(this, launchList -> {

            Launch launch = launchList.get(0);
            textView8.append("\n" + launch.getName());
            for (Program program : launch.getProgramList()) {
                programViewModel.getProgramById(program.getId());
            }
            padViewModel.getPadById(launch.getPad().getId());
//            for (FirstStage firstStage : launch.getRocket().getLauncherStage()) {
//                launcherViewModel.getLauncherById(firstStage.getLauncher().getId());
//            }
        });

        TextView textView9 = findViewById(R.id.textView9);
        programViewModel.getLiveData().observe(this, programList ->
                textView9.append("\n" + programList.get(0).getName()));

        TextView textView10 = findViewById(R.id.textView10);
        expeditionViewModel.getLiveData().observe(this, expeditionList -> {

            Expedition expedition = expeditionList.get(0);
            textView10.append("\n" + expedition.getName());
            for (AstronautFlight astronautFlight : expedition.getCrew()) {
                astronautViewModel.getAstronautById(astronautFlight.getAstronaut().getId());
            }
        });

        TextView textView11 = findViewById(R.id.textView11);
        astronautViewModel.getLiveData().observe(this, astronautList ->
                textView11.append("\n" + astronautList.get(0).getName()));

        TextView textView12 = findViewById(R.id.textView12);
        spaceStationViewModel.getLiveData().observe(this, spaceStationList -> {

            SpaceStation spaceStation = spaceStationList.get(0);
            textView12.append("\n" + spaceStation.getName());
            for (DockingLocation dockingLocation : spaceStation.getDockingLocationList()) {
                if (dockingLocation.getDockingEvent() != null) {
                    dockingEventViewModel
                            .getDockingEventById(dockingLocation.getDockingEvent().getId());
                }
            }
        });

        TextView textView13 = findViewById(R.id.textView13);
        dockingEventViewModel.getLiveData().observe(this, dockingEventList -> {

            DockingEvent dockingEvent = dockingEventList.get(0);
            textView13.append("\n" + dockingEvent.getDocking().toString());
            spacecraftFlightViewModel.getSpacecraftFlightById(
                    dockingEvent.getFlightVehicle().getId());
        });

        TextView textView14 = findViewById(R.id.textView14);
        padViewModel.getLiveData().observe(this, padList -> {

            Pad pad = padList.get(0);
            textView14.append("\n" + pad.getName());
            locationViewModel.getLocationById(pad.getLocation().getId());
        });

        TextView textView15 = findViewById(R.id.textView15);
        locationViewModel.getLiveData().observe(this, locationList ->
                textView15.append("\n" + locationList.get(0).getName()));

        TextView textView16 = findViewById(R.id.textView16);
        spacecraftFlightViewModel.getLiveData().observe(this, spacecraftFlightList -> {

            SpacecraftFlight spacecraftFlight = spacecraftFlightList.get(0);
            textView16.append("\n" + spacecraftFlight.getDestination());
            spacecraftViewModel.getSpacecraftById(spacecraftFlight.getSpacecraft().getId());
        });

        TextView textView17 = findViewById(R.id.textView17);
        spacecraftViewModel.getLiveData().observe(this, spacecraftList ->
                textView17.append("\n" + spacecraftList.get(0).getName()));

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