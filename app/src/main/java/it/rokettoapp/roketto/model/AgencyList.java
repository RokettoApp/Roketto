package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AgencyList {

    @SerializedName("count")
    private int mCount;

    @SerializedName("next")
    private String mNext;

    @SerializedName("results")
    private List<Agency> mAgencies;

    public AgencyList() { }

    public AgencyList(int count, String next, List<Agency> agencies) {

        this.mCount = count;
        this.mNext = next;
        this.mAgencies = agencies;
    }

    public int getCount() {

        return mCount;
    }

    public void setCount(String next) {

        this.mNext = next;
    }

    public String getNext() {

        return mNext;
    }

    public void setNext(int count) {

        this.mCount = count;
    }

    public List<Agency> getAgencies() {

        return mAgencies;
    }

    public void setAgencies(List<Agency> agencies) {

        this.mAgencies = agencies;
    }
}
