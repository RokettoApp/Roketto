package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseList<T> implements Serializable {

    @SerializedName("count")
    private int mCount;

    @SerializedName("next")
    private String mNext;

    @SerializedName("results")
    private List<T> mResults;

    private String message;

    private boolean isError;

    public ResponseList() { }

    public ResponseList(int count, String next, List<T> results) {

        this.mCount = count;
        this.mNext = next;
        this.mResults = results;
        this.message = "";
        isError = false;
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

    public List<T> getResults() {

        return mResults;
    }

    public void setResults(List<T> results) {

        this.mResults = results;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public boolean isError() {

        return isError;
    }

    public void setError(boolean error) {

        isError = error;
    }
}
