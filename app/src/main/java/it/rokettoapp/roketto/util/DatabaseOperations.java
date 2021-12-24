package it.rokettoapp.roketto.util;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.database.GenericDao;
import it.rokettoapp.roketto.database.RokettoDatabase;

public class DatabaseOperations<K, V> {

    GenericDao<K, V> dao;

    public DatabaseOperations(GenericDao<K, V> dao) {

        this.dao = dao;
    }

    public void saveList(List<V> list) {

        RokettoDatabase.databaseWriteExecutor.execute(() ->
                dao.insertList(list));
    }

    public void saveValue(V value) {

        RokettoDatabase.databaseWriteExecutor.execute(() -> dao.insert(value));
    }

    public void getListFromDatabase(MutableLiveData<List<V>> liveData) {

        new Thread(() -> liveData.postValue(dao.getAll())).start();
    }

}
