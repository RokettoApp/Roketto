package it.rokettoapp.roketto.util;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.database.GenericDao;
import it.rokettoapp.roketto.database.RokettoDatabase;
import it.rokettoapp.roketto.model.ResponseList;

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

    public void getListFromDatabase(MutableLiveData<ResponseList<V>> liveData) {

        new Thread(() -> {
            ResponseList<V> responseList = new ResponseList<>();
            responseList.setResults(dao.getAll());
            if (responseList.getResults() == null) {
                responseList.setError(true);
                responseList.setMessage("Error reading from db");
            }
            liveData.postValue(responseList);
        }).start();
    }

    public void deleteAll() {

        RokettoDatabase.databaseWriteExecutor.execute(() -> dao.deleteAll());
    }
}
