package it.rokettoapp.roketto.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.rokettoapp.roketto.model.Agency;
import it.rokettoapp.roketto.model.Astronaut;
import it.rokettoapp.roketto.model.Spacecraft;

@Database(entities = {Agency.class, Astronaut.class, Spacecraft.class}, version =1)
@TypeConverters({Converters.class})
public abstract class RokettoDatabase extends RoomDatabase {
    public abstract AgencyDao agencyDao();
    public abstract AstronautDao astronautDao();
    public abstract SpacecraftDao spacecraftDao();

    private static volatile RokettoDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RokettoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RokettoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RokettoDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
