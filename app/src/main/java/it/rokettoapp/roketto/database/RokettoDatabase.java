package it.rokettoapp.roketto.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.Article;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.DockingEvent;
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.Launch;
import it.rokettoapp.roketto.model.Launcher;
import it.rokettoapp.roketto.model.Location;
import it.rokettoapp.roketto.model.Pad;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.SpaceStation;
import it.rokettoapp.roketto.model.Spacecraft;
import it.rokettoapp.roketto.model.SpacecraftFlight;

@Database(entities = {Agency.class, Astronaut.class, Spacecraft.class, Article.class, Event.class,
        Program.class, Expedition.class, Launch.class, SpaceStation.class, DockingEvent.class,
        SpacecraftFlight.class, Pad.class, Location.class, Launcher.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class RokettoDatabase extends RoomDatabase {

    public abstract AgencyDao agencyDao();
    public abstract ArticleDao articleDao();
    public abstract AstronautDao astronautDao();
    public abstract DockingEventDao dockingEventDao();
    public abstract EventDao eventDao();
    public abstract ExpeditionDao expeditionDao();
    public abstract LaunchDao launchDao();
    public abstract ProgramDao programDao();
    public abstract SpacecraftDao spacecraftDao();
    public abstract SpacecraftFlightDao spacecraftFlightDao();
    public abstract SpaceStationDao spaceStationDao();
    public abstract PadDao padDao();
    public abstract LocationDao locationDao();
    public abstract LauncherDao launcherDao();

    private static volatile RokettoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RokettoDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (RokettoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RokettoDatabase.class, "roketto_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
