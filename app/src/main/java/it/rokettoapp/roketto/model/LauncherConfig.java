package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LauncherConfig {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("family")
    private String mFamily;

    @SerializedName("full_name")
    private String mFullName;

    @SerializedName("manufacturer")
    private Agency mManufacturer;

    @SerializedName("program")
    private List<Program> mProgramList;

    @SerializedName("variant")
    private String mVariant;

    @SerializedName("alias")
    private String mAlias;

    @SerializedName("min_stage")
    private int mMinStage;

    @SerializedName("max_stage")
    private int mMaxStage;

    @SerializedName("length")
    private float mLength;

    @SerializedName("diameter")
    private float mDiameter;

    @SerializedName("maiden_flight")
    private String mMaidenFlight;

    @SerializedName("launch_cost")
    private String mLaunchCost;

    @SerializedName("launch_mass")
    private int mLaunchMass;

    @SerializedName("leo_capacity")
    private int mLeoCapacity;

    @SerializedName("gto_capacity")
    private int mGtoCapacity;

    @SerializedName("to_thrust")
    private int mToThrust;

    @SerializedName("apogee")
    private int mApogee;

    @SerializedName("vehicle_range")
    private int mVehicleRange;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("info_url")
    private String mInfoUrl;

    @SerializedName("wiki_url")
    private String mWikipedia;

    @SerializedName("total_launch_count")
    private int mTotalLaunchCount;

    @SerializedName("consecutive_successful_launches")
    private int mConsecutiveSuccessfulLaunches;

    @SerializedName("successful_launches")
    private int mSuccessfulLaunches;

    @SerializedName("failed_launches")
    private int mFailedLaunches;

    @SerializedName("pending_launches")
    private int mPendingLaunches;

    public LauncherConfig(String id, String name, String description, String family,
                          String fullName, Agency manufacturer, List<Program> programList,
                          String variant, String alias, int minStage, int maxStage,
                          float length, float diameter, String maidenFlight, String launchCost,
                          int launchMass, int leoCapacity, int gtoCapacity, int toThrust,
                          int apogee, int vehicleRange, String imageUrl, String infoUrl,
                          String wikipedia, int totalLaunchCount,
                          int consecutiveSuccessfulLaunches, int successfulLaunches,
                          int failedLaunches, int pendingLaunches) {

        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mFamily = family;
        this.mFullName = fullName;
        this.mManufacturer = manufacturer;
        this.mProgramList = programList;
        this.mVariant = variant;
        this.mAlias = alias;
        this.mMinStage = minStage;
        this.mMaxStage = maxStage;
        this.mLength = length;
        this.mDiameter = diameter;
        this.mMaidenFlight = maidenFlight;
        this.mLaunchCost = launchCost;
        this.mLaunchMass = launchMass;
        this.mLeoCapacity = leoCapacity;
        this.mGtoCapacity = gtoCapacity;
        this.mToThrust = toThrust;
        this.mApogee = apogee;
        this.mVehicleRange = vehicleRange;
        this.mImageUrl = imageUrl;
        this.mInfoUrl = infoUrl;
        this.mWikipedia = wikipedia;
        this.mTotalLaunchCount = totalLaunchCount;
        this.mConsecutiveSuccessfulLaunches = consecutiveSuccessfulLaunches;
        this.mSuccessfulLaunches = successfulLaunches;
        this.mFailedLaunches = failedLaunches;
        this.mPendingLaunches = pendingLaunches;
    }

    public String getId() {

        return mId;
    }

    public void setId(String id) {

        this.mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(String name) {

        this.mName = name;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public String getFamily() {

        return mFamily;
    }

    public void setFamily(String family) {

        this.mFamily = family;
    }

    public String getFullName() {

        return mFullName;
    }

    public void setFullName(String fullName) {

        this.mFullName = fullName;
    }

    public Agency getManufacturer() {

        return mManufacturer;
    }

    public void setManufacturer(Agency manufacturer) {

        this.mManufacturer = manufacturer;
    }

    public List<Program> getProgramList() {

        return mProgramList;
    }

    public void setProgramList(List<Program> programList) {

        this.mProgramList = programList;
    }

    public String getVariant() {

        return mVariant;
    }

    public void setVariant(String variant) {

        this.mVariant = variant;
    }

    public String getAlias() {

        return mAlias;
    }

    public void setAlias(String alias) {

        this.mAlias = alias;
    }

    public int getMinStage() {

        return mMinStage;
    }

    public void setMinStage(int minStage) {

        this.mMinStage = minStage;
    }

    public int getMaxStage() {

        return mMaxStage;
    }

    public void setMaxStage(int maxStage) {

        this.mMaxStage = maxStage;
    }

    public float getLength() {

        return mLength;
    }

    public void setLength(float length) {

        this.mLength = length;
    }

    public float getDiameter() {

        return mDiameter;
    }

    public void setDiameter(float diameter) {

        this.mDiameter = diameter;
    }

    public String getMaidenFlight() {

        return mMaidenFlight;
    }

    public void setMaidenFlight(String maidenFlight) {

        this.mMaidenFlight = maidenFlight;
    }

    public String getLaunchCost() {

        return mLaunchCost;
    }

    public void setLaunchCost(String launchCost) {

        this.mLaunchCost = launchCost;
    }

    public int getLaunchMass() {

        return mLaunchMass;
    }

    public void setLaunchMass(int launchMass) {

        this.mLaunchMass = launchMass;
    }

    public int getLeoCapacity() {

        return mLeoCapacity;
    }

    public void setLeoCapacity(int leoCapacity) {

        this.mLeoCapacity = leoCapacity;
    }

    public int getGtoCapacity() {

        return mGtoCapacity;
    }

    public void setGtoCapacity(int gtoCapacity) {

        this.mGtoCapacity = gtoCapacity;
    }

    public int getToThrust() {

        return mToThrust;
    }

    public void setToThrust(int toThrust) {

        this.mToThrust = toThrust;
    }

    public int getApogee() {

        return mApogee;
    }

    public void setApogee(int apogee) {

        this.mApogee = apogee;
    }

    public int getVehicleRange() {

        return mVehicleRange;
    }

    public void setVehicleRange(int vehicleRange) {

        this.mVehicleRange = vehicleRange;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public String getInfoUrl() {

        return mInfoUrl;
    }

    public void setInfoUrl(String infoUrl) {

        this.mInfoUrl = infoUrl;
    }

    public String getWikipedia() {

        return mWikipedia;
    }

    public void setWikipedia(String wikipedia) {

        this.mWikipedia = wikipedia;
    }

    public int getTotalLaunchCount() {

        return mTotalLaunchCount;
    }

    public void setTotalLaunchCount(int totalLaunchCount) {

        this.mTotalLaunchCount = totalLaunchCount;
    }

    public int getConsecutiveSuccessfulLaunches() {

        return mConsecutiveSuccessfulLaunches;
    }

    public void setConsecutiveSuccessfulLaunches(int consecutiveSuccessfulLaunches) {

        this.mConsecutiveSuccessfulLaunches = consecutiveSuccessfulLaunches;
    }

    public int getSuccessfulLaunches() {

        return mSuccessfulLaunches;
    }

    public void setSuccessfulLaunches(int successfulLaunches) {

        this.mSuccessfulLaunches = successfulLaunches;
    }

    public int getFailedLaunches() {

        return mFailedLaunches;
    }

    public void setFailedLaunches(int failedLaunches) {

        this.mFailedLaunches = failedLaunches;
    }

    public int getPendingLaunches() {

        return mPendingLaunches;
    }

    public void setPendingLaunches(int pendingLaunches) {

        this.mPendingLaunches = pendingLaunches;
    }
}
