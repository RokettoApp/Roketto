package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AstronautResponse {

    @SerializedName("count")
    private int mCount;

    @SerializedName("results")
    private List<Astronaut> mAstronauts;

    public AstronautResponse() { }

    public AstronautResponse(int count, List<Astronaut> astronauts) {

        this.mCount = count;
        this.mAstronauts = astronauts;
    }

    public int getCount() {

        return mCount;
    }

    public void setCount(int count) {

        this.mCount = count;
    }

    public List<Astronaut> getAstronauts() {

        return mAstronauts;
    }

    public void setAstronauts(List<Astronaut> astronauts) {

        this.mAstronauts = astronauts;
    }
}
