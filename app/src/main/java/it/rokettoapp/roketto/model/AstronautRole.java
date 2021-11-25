package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class AstronautRole {

    @SerializedName("id")
    int mId;

    @SerializedName("role")
    String mRole;

    @SerializedName("priority")
    int mPriority;

    public AstronautRole(int id, String role, int priority) {

        this.mId = id;
        this.mRole = role;
        this.mPriority = priority;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getRole() {

        return mRole;
    }

    public void setRole(String role) {

        this.mRole = role;
    }

    public int getPriority() {

        return mPriority;
    }

    public void setPriority(int priority) {

        this.mPriority = priority;
    }
}
