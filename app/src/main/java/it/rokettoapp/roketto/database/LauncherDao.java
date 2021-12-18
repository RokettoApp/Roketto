package it.rokettoapp.roketto.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import it.rokettoapp.roketto.model.Launcher;

@Dao
public interface LauncherDao extends GenericDao<Integer, Launcher> {

    @Override
    @Query("SELECT * FROM launcher")
    List<Launcher> getAll();

    @Override
    @Query("SELECT * " +
           "FROM launcher " +
           "WHERE mId = :id")
    Launcher getById(Integer id);

    @Override
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertList(List<Launcher> launcherList);

    @Override
    @Insert
    void insert(Launcher launcher);

    @Override
    @Delete
    void delete(Launcher launcher);

    @Override
    @Query("DELETE FROM launcher")
    void deleteAll();
}
