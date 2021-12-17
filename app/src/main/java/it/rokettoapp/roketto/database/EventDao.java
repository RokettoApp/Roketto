package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Event;
import it.rokettoapp.roketto.model.SpaceStation;

@Dao
public interface EventDao extends GenericDao<Integer, Event> {

    @Override
    @Query("SELECT * FROM event")
    List<Event> getAll();

    @Override
    @Query("SELECT * " +
            "FROM event " +
            "WHERE mId = :id")
    Event getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Event> eventList);

    @Override
    @Insert
    void insert(Event event);

    @Override
    @Delete
    void delete(Event event);

    @Override
    @Query("DELETE FROM event")
    void deleteAll();
}
