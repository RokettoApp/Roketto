package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class EventType {

    @SerializedName("id")
    int mId;

    @SerializedName("name")
    String mName;

    public EventType(int id, String name) {

        this.mId = id;
        this.mName = name;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }
}
