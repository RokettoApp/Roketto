package it.rokettoapp.roketto.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Event;

@Dao
public interface EventDao {
    @Query("SELECT * FROM favorite_event")
    LiveData<List<Event>> getAll();

    @Insert
    void insertEventList(List<Event> eventList);

    @Insert
    void insertEvent(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM favorite_event")
    void deleteAll();
}
