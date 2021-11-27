package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Event;

@Dao
public interface EventDao {
    @Query("SELECT * FROM favorite_event")
    List<Event> getAll();

    @Insert
    void insertAgencyList(List<Event> eventList);

    @Insert
    void insertAgency(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM favorite_event")
    void deleteAll();
}
