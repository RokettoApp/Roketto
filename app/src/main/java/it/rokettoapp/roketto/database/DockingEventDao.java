package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.DockingEvent;

@Dao
public interface DockingEventDao extends GenericDao<Integer, DockingEvent> {

    @Override
    @Query("SELECT * FROM docking_event")
    List<DockingEvent> getAll();

    @Override
    @Query("SELECT * " +
           "FROM docking_event " +
           "WHERE mId = :id")
    DockingEvent getById(Integer id);

    @Override
    @Insert
    void insertList(List<DockingEvent> dockingEventList);

    @Override
    @Insert
    void insert(DockingEvent dockingEvent);

    @Override
    @Delete
    void delete(DockingEvent dockingEvent);

    @Override
    @Query("DELETE FROM docking_event")
    void deleteAll();
}
