package it.rokettoapp.roketto.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "astronaut")
public class Astronaut implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @Embedded(prefix = "status_")
    @SerializedName("status")
    private AstronautStatus mStatus;

    @Embedded(prefix = "type_")
    @SerializedName("type")
    private AstronautType mType;

    @SerializedName("date_of_birth")
    private Date mDateOfBirth;

    @SerializedName("date_of_death")
    private Date mDateOfDeath;

    @SerializedName("nationality")
    private String mNationality;

    @SerializedName("bio")
    private String mBiography;

    @SerializedName("twitter")
    private String mTwitter;

    @SerializedName("instagram")
    private String mInstagram;

    @SerializedName("wiki")
    private String mWikipedia;

    @SerializedName("agency")
    private Agency mAgency;

    @SerializedName("profile_image")
    private String mProfileImage;

    @SerializedName("flights")
    private List<Launch> mFlightList;

    @SerializedName("landings")
    private List<SpacecraftFlight> mLandings;

    @SerializedName("last_flight")
    private Date mLastFlight;

    @SerializedName("first_flight")
    private Date mFirstFlight;

    @Ignore
    public Astronaut(int id) {

        this.mId = id;
    }

    public Astronaut(int id, String name, AstronautStatus status, AstronautType type, Date dateOfDeath, Date dateOfBirth,
                     String nationality, String biography, String twitter, String instagram, String wikipedia,
                     Agency agency, String profileImage, List<Launch> flightList,
                     List<SpacecraftFlight> landings, Date lastFlight, Date firstFlight) {

        this.mId = id;
        this.mName = name;
        this.mStatus = status;
        this.mType = type;
        this.mDateOfDeath = dateOfDeath;
        this.mDateOfBirth = dateOfBirth;
        this.mNationality = nationality;
        this.mBiography = biography;
        this.mTwitter = twitter;
        this.mInstagram = instagram;
        this.mWikipedia = wikipedia;
        this.mAgency = agency;
        this.mProfileImage = profileImage;
        this.mFlightList = flightList;
        this.mLandings = landings;
        this.mLastFlight = lastFlight;
        this.mFirstFlight = firstFlight;
    }

    public static Astronaut buildMinAstronaut(Astronaut astronaut) {

        Astronaut minAstronaut = new Astronaut(astronaut.getId());
        minAstronaut.setName(astronaut.getName());
        minAstronaut.setProfileImage(astronaut.getProfileImage());
        minAstronaut.setNationality(astronaut.getNationality());
        return minAstronaut;
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

    public AstronautStatus getStatus() {

        return mStatus;
    }

    public void setStatus(AstronautStatus status) {

        this.mStatus = status;
    }

    public AstronautType getType() {

        return mType;
    }

    public void setType(AstronautType type) {

        this.mType = type;
    }

    public Date getDateOfDeath() {

        return mDateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {

        this.mDateOfDeath = dateOfDeath;
    }

    public Date getDateOfBirth() {

        return mDateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.mDateOfBirth = dateOfBirth;
    }

    public String getNationality() {

        return mNationality;
    }

    public void setNationality(String nationality) {

        this.mNationality = nationality;
    }

    public String getBiography() {

        return mBiography;
    }

    public void setBiography(String biography) {

        this.mBiography = biography;
    }

    public String getTwitter() {

        return mTwitter;
    }

    public void setTwitter(String twitter) {

        this.mTwitter = twitter;
    }

    public String getInstagram() {

        return mInstagram;
    }

    public void setInstagram(String instagram) {

        this.mInstagram = instagram;
    }

    public String getWikipedia() {

        return mWikipedia;
    }

    public void setWikipedia(String wikipedia) {

        this.mWikipedia = wikipedia;
    }

    public Agency getAgency() {

        return mAgency;
    }

    public void setAgency(Agency agency) {

        this.mAgency = agency;
    }

    public String getProfileImage() {

        return mProfileImage;
    }

    public void setProfileImage(String profileImage) {

        this.mProfileImage = profileImage;
    }

    public List<Launch> getFlightList() {

        return mFlightList;
    }

    public void setFlightList(List<Launch> flightList) {

        this.mFlightList = flightList;
    }

    public List<SpacecraftFlight> getLandings() {

        return mLandings;
    }

    public void getLandings(List<SpacecraftFlight> landings) {

        this.mLandings = landings;
    }

    public Date getLastFlight() {

        return mLastFlight;
    }

    public void setLastFlight(Date lastFlight) {

        this.mLastFlight = lastFlight;
    }

    public Date getFirstFlight() {

        return mFirstFlight;
    }

    public void setFirstFlight(Date firstFlight) {

        this.mFirstFlight = firstFlight;
    }
}
