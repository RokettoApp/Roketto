package it.rokettoapp.roketto.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String email;
    private List<Integer> favouriteEvents;

    public User() {

        // Required by Firebase
    }

    public User(String id, String email) {

        this.id = id;
        this.email = email;
        favouriteEvents = new ArrayList<>();
    }

    public User(String id, String email, List<Integer> eventIds) {

        this.id = id;
        this.email = email;
        this.favouriteEvents = eventIds;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public List<Integer> getFavouriteEvents() {

        return favouriteEvents;
    }

    public void setFavouriteEvents(List<Integer> eventIds) {

        this.favouriteEvents = eventIds;
    }

    public void addFavouriteEvent(Integer eventId) {

        favouriteEvents.add(eventId);
    }

    public void removeFavouriteEvent(Integer eventId) {

        favouriteEvents.remove(eventId);
    }
}
