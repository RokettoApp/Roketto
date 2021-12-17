package it.rokettoapp.roketto.database;

import java.util.List;

public interface GenericDao<K, V> {

    List<V> getAll();

    V getById(K id);

    void insertList(List<V> list);

    void insert(V value);

    void delete(V value);

    void deleteAll();
}
