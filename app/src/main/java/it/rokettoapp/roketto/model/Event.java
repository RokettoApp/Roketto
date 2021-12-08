package it.rokettoapp.roketto.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(tableName = "event")
public class Event implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("slug")
    private String mSlug;

    @SerializedName("name")
    private String mName;

    @SerializedName("updates")
    private List<Update> mUpdates;

    @Embedded(prefix = "type_")
    @SerializedName("type")
    private EventType mEventType;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("location")
    private String mLocation;

    @SerializedName("news_url")
    private String mNewsUrl;

    @SerializedName("video_url")
    private String mVideoUrl;

    @SerializedName("feature_image")
    private String mFeatureImage;

    @SerializedName("date")
    private Date mDate;

    @Ignore
    @SerializedName("launches")
    private List<Launch> mLaunchList;

    @Ignore
    @SerializedName("expeditions")
    private List<Expedition> mExpeditionList;

    @Ignore
    @SerializedName("spacestations")
    private List<SpaceStation> mSpaceStationList;

    @Ignore
    @SerializedName("program")
    private List<Program> mProgramList;

    public Event(int id, String slug, String name, EventType eventType,
                 String description, String location, String newsUrl, String videoUrl,
                 String featureImage, Date date) {

        this.mId = id;
        this.mSlug = slug;
        this.mName = name;
        this.mEventType = eventType;
        this.mDescription = description;
        this.mLocation = location;
        this.mNewsUrl = newsUrl;
        this.mVideoUrl = videoUrl;
        this.mFeatureImage = featureImage;
        this.mDate = date;
    }

    public Event(int id, String slug, String name, List<Update> updates, EventType eventType,
                 String description, String location, String newsUrl, String videoUrl,
                 String featureImage, Date date, List<Launch> launchList,
                 List<Expedition> expeditionList, List<SpaceStation> spaceStationList,
                 List<Program> programList) {

        this.mId = id;
        this.mSlug = slug;
        this.mName = name;
        this.mUpdates = updates;
        this.mEventType = eventType;
        this.mDescription = description;
        this.mLocation = location;
        this.mNewsUrl = newsUrl;
        this.mVideoUrl = videoUrl;
        this.mFeatureImage = featureImage;
        this.mDate = date;
        this.mLaunchList = launchList;
        this.mExpeditionList = expeditionList;
        this.mSpaceStationList = spaceStationList;
        this.mProgramList = programList;
    }

    public int getId() {

        return mId;
    }

    public void setId(int id) {

        this.mId = id;
    }

    public String getSlug() {

        return mSlug;
    }

    public void setSlug(String slug) {

        this.mSlug = slug;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }

    public List<Update> getUpdates() {

        return mUpdates;
    }

    public void setUpdates(List<Update> updates) {

        this.mUpdates = updates;
    }

    public EventType getEventType() {

        return mEventType;
    }

    public void setEventType(EventType eventType) {

        this.mEventType = eventType;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public String getLocation() {

        return mLocation;
    }

    public void setLocation(String location) {

        this.mLocation = location;
    }

    public String getNewsUrl() {

        return mNewsUrl;
    }

    public void setNewsUrl(String newsUrl) {

        this.mNewsUrl = newsUrl;
    }

    public String getVideoUrl() {

        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {

        this.mVideoUrl = videoUrl;
    }

    public String getFeatureImage() {

        return mFeatureImage;
    }

    public void setFeatureImage(String featureImage) {

        this.mFeatureImage = featureImage;
    }

    public Date getDate() {

        return mDate;
    }

    public void setDate(Date date) {

        this.mDate = date;
    }

    public List<Launch> getLaunchList() {

        return mLaunchList;
    }

    public void setLaunchList(List<Launch> launchList) {

        this.mLaunchList = launchList;
    }

    public List<Expedition> getExpeditionList() {

        return mExpeditionList;
    }

    public void setExpeditionList(List<Expedition> expeditionList) {

        this.mExpeditionList = expeditionList;
    }

    public List<SpaceStation> getSpaceStationList() {

        return mSpaceStationList;
    }

    public void setSpaceStationList(List<SpaceStation> spaceStationList) {

        this.mSpaceStationList = spaceStationList;
    }

    public List<Program> getProgramList() {

        return mProgramList;
    }

    public void setProgramList(List<Program> programList) {

        this.mProgramList = programList;
    }
}