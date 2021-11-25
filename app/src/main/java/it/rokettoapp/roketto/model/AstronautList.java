package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AstronautList {

    @SerializedName("count")
    private int mCount;

    @SerializedName("next")
    private String mNext;

    @SerializedName("results")
    private List<Astronaut> mAstronauts;

    public AstronautList() { }

    public AstronautList(int count, String next, List<Astronaut> astronauts) {

        this.mCount = count;
        this.mNext = next;
        this.mAstronauts = astronauts;
    }

    public int getCount() {

        return mCount;
    }

    public void setCount(int count) {

        this.mCount = count;
    }

    public String getNext() {

        return mNext;
    }

    public void setNext(int count) {

        this.mCount = count;
    }

    public List<Astronaut> getAstronauts() {

        return mAstronauts;
    }

    public void setAstronauts(List<Astronaut> astronauts) {

        this.mAstronauts = astronauts;
    }
}
