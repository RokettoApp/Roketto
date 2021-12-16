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
import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.model.Mission;
import it.rokettoapp.roketto.model.MissionPatch;
import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.Spacecraft;

@Database(entities = {Agency.class, Astronaut.class, Spacecraft.class, Article.class, Event.class,
        Mission.class, Program.class, MissionPatch.class, Expedition.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class RokettoDatabase extends RoomDatabase {

    public abstract AgencyDao agencyDao();
    public abstract AstronautDao astronautDao();
    public abstract SpacecraftDao spacecraftDao();
    public abstract ArticleDao articleDao();
    public abstract EventDao eventDao();
    public abstract MissionDao missionDao();
    public abstract ProgramDao programDao();
    public abstract ExpeditionDao expeditionDao();

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
