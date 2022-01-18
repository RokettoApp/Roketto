package it.rokettoapp.roketto.ui;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.repository.AgencyRepository;
import it.rokettoapp.roketto.repository.ArticleRepository;
import it.rokettoapp.roketto.repository.AstronautRepository;
import it.rokettoapp.roketto.repository.EventRepository;
import it.rokettoapp.roketto.repository.ExpeditionRepository;
import it.rokettoapp.roketto.repository.LaunchRepository;
import it.rokettoapp.roketto.util.Constants;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;

public class FragmentSettings extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            clearCache();
            SharedPreferencesProvider sharedPreferencesProvider =
                    new SharedPreferencesProvider(requireActivity().getApplication());
            sharedPreferencesProvider.deleteAll();
            startActivity(new Intent(requireActivity(), AuthenticationActivity.class));
            requireActivity().finish();
        });

        Button clearCacheButton = view.findViewById(R.id.clearCacheButton);
        clearCacheButton.setOnClickListener(view1 -> {

            clearCache();
            SharedPreferencesProvider sharedPreferencesProvider =
                    new SharedPreferencesProvider(requireActivity().getApplication());
            sharedPreferencesProvider.clearAllLastUpdates();
        });

        Button aboutLaunchApiButton = view.findViewById(R.id.aboutLaunchApiButton);
        aboutLaunchApiButton.setOnClickListener(view1 -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(Constants.TSD_URL));
            startActivity(intent);
        });

        Button aboutNewsApiButton = view.findViewById(R.id.aboutNewsApiButton);
        aboutNewsApiButton.setOnClickListener(view1 -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(Constants.SFN_URL));
            startActivity(intent);
        });

        return view;
    }

    public void clearCache() {

        Application application = requireActivity().getApplication();
        AgencyRepository agencyRepository = new AgencyRepository(application);
        agencyRepository.clearAgencies();
        ArticleRepository articleRepository = new ArticleRepository(application);
        articleRepository.clearArticles();
        AstronautRepository astronautRepository = new AstronautRepository(application);
        astronautRepository.clearAstronauts();
//            DockingEventRepository dockingEventRepository = new DockingEventRepository(application);
//            dockingEventRepository.clearDockingEvents();
        EventRepository eventRepository = new EventRepository(application);
        eventRepository.clearEvents();
        ExpeditionRepository expeditionRepository = new ExpeditionRepository(application);
        expeditionRepository.clearExpeditions();
        LaunchRepository launchRepository = new LaunchRepository(application);
        launchRepository.clearLaunches();
//            LauncherRepository launcherRepository = new LauncherRepository(application);
//            launcherRepository.clearLaunchers();
//            LocationRepository locationRepository = new LocationRepository(application);
//            locationRepository.clearLocations();
//            PadRepository padRepository = new PadRepository(application);
//            padRepository.clearPads();
//            ProgramRepository programRepository = new ProgramRepository(application);
//            programRepository.clearPrograms();
//            SpacecraftFlightRepository spacecraftFlightRepository =
//                    new SpacecraftFlightRepository(application);
//            spacecraftFlightRepository.clearSpacecraftFlight();
//            SpacecraftRepository spacecraftRepository = new SpacecraftRepository(application);
//            spacecraftRepository.clearSpacecrafts();
//            SpaceStationRepository spaceStationRepository = new SpaceStationRepository(application);
//            spaceStationRepository.clearSpaceStations();
    }
}
