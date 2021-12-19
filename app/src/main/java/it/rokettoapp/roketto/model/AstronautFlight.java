package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AstronautFlight implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("role")
    private AstronautRole mRole;

    @SerializedName("astronaut")
    private Astronaut mAstronaut;

    public AstronautFlight(int id, AstronautRole role, Astronaut astronaut) {

        this.mId = id;
        this.mRole = role;
        this.mAstronaut = astronaut;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public AstronautRole getRole() {

        return mRole;
    }

    public void setRole(AstronautRole role) {

        this.mRole = role;
    }

    public Astronaut getAstronaut() {

        return mAstronaut;
    }

    public void setAstronaut(Astronaut astronaut) {

        this.mAstronaut = astronaut;
    }
}
