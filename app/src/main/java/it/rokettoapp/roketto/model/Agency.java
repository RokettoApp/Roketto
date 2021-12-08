package it.rokettoapp.roketto.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "agency")
public class Agency {

    @PrimaryKey
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("featured")
    private boolean mFeatured;

    @SerializedName("type")
    private String mType;

    @SerializedName("country_code")
    private String mCountryCode;

    @SerializedName("abbrev")
    private String mAbbreviation;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("administrator")
    private String mAdministrator;

    @SerializedName("founding_year")
    private String mFoundingYear;

    @SerializedName("launchers")
    private String mLaunchers;

    @SerializedName("spacecraft")
    private String mSpacecraft;

    @SerializedName("parent")
    private String mParent;

    @SerializedName("launch_library_url")
    private String mLaunchLibraryUrl;

    @SerializedName("total_launch_count")
    private int mTotalLaunchCount;

    @SerializedName("successful_launches")
    private int mSuccessfulLaunches;

    @SerializedName("consecutive_successful_launches")
    private int mConsecutiveSuccessfulLaunches;

    @SerializedName("failed_launches")
    private int mFailedLaunches;

    @SerializedName("pending_launches")
    private int mPendingLaunches;

    @SerializedName("successful_landings")
    private int mSuccessfulLandings;

    @SerializedName("failed_landings")
    private int mFailedLandings;

    @SerializedName("attempted_landings")
    private int mAttemptedLandings;

    @SerializedName("consecutive_successful_landings")
    private int mConsecutiveSuccessfulLandings;

    @SerializedName("info_url")
    private String mInfoUrl;

    @SerializedName("wiki_url")
    private String mWikipedia;

    @SerializedName("logo_url")
    private String mLogoUrl;

    @SerializedName("image_url")
    private String mImageUrl;

    @SerializedName("nation_url")
    private String mNationUrl;

    @SerializedName("launcher_list")
    private List<LauncherConfig> mLauncherList;

    @SerializedName("spacecraft_list")
    private List<SpacecraftConfiguration> mSpacecraftList;

    @Ignore
    public Agency(int id) {
        this.mId = id;
    }

    public Agency(int id, String name, boolean featured, String type, String countryCode,
                  String abbreviation, String description, String administrator,
                  String foundingYear, String launchers, String spacecraft, String parent,
                  String launchLibraryUrl, int totalLaunchCount, int successfulLaunches,
                  int consecutiveSuccessfulLaunches, int failedLaunches, int pendingLaunches,
                  int successfulLandings, int failedLandings, int attemptedLandings,
                  int consecutiveSuccessfulLandings, String infoUrl, String wikipedia,
                  String logoUrl, String imageUrl, String nationUrl,
                  List<LauncherConfig> launcherList,
                  List<SpacecraftConfiguration> spacecraftList) {

        this.mId = id;
        this.mName = name;
        this.mFeatured = featured;
        this.mType = type;
        this.mCountryCode = countryCode;
        this.mAbbreviation = abbreviation;
        this.mDescription = description;
        this.mAdministrator = administrator;
        this.mFoundingYear = foundingYear;
        this.mLaunchers = launchers;
        this.mSpacecraft = spacecraft;
        this.mParent = parent;
        this.mLaunchLibraryUrl = launchLibraryUrl;
        this.mTotalLaunchCount = totalLaunchCount;
        this.mSuccessfulLaunches = successfulLaunches;
        this.mConsecutiveSuccessfulLaunches = consecutiveSuccessfulLaunches;
        this.mFailedLaunches = failedLaunches;
        this.mPendingLaunches = pendingLaunches;
        this.mSuccessfulLandings = successfulLandings;
        this.mFailedLandings = failedLandings;
        this.mAttemptedLandings = attemptedLandings;
        this.mConsecutiveSuccessfulLandings = consecutiveSuccessfulLandings;
        this.mInfoUrl = infoUrl;
        this.mWikipedia = wikipedia;
        this.mLogoUrl = logoUrl;
        this.mImageUrl = imageUrl;
        this.mNationUrl = nationUrl;
        this.mLauncherList = launcherList;
        this.mSpacecraftList = spacecraftList;
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

    public boolean isFeatured() {

        return mFeatured;
    }

    public void setFeatured(boolean featured) {

        this.mFeatured = featured;
    }

    public String getType() {

        return mType;
    }

    public void setType(String type) {

        this.mType = type;
    }

    public String getCountryCode() {

        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {

        this.mCountryCode = countryCode;
    }

    public String getAbbreviation() {

        return mAbbreviation;
    }

    public void setAbbreviation(String abbreviation) {

        this.mAbbreviation = abbreviation;
    }

    public String getDescription() {

        return mDescription;
    }

    public void setDescription(String description) {

        this.mDescription = description;
    }

    public String getAdministrator() {

        return mAdministrator;
    }

    public void setAdministrator(String administrator) {

        this.mAdministrator = administrator;
    }

    public String getFoundingYear() {

        return mFoundingYear;
    }

    public void setFoundingYear(String foundingYear) {

        this.mFoundingYear = foundingYear;
    }

    public String getLaunchers() {

        return mLaunchers;
    }

    public void setLaunchers(String launchers) {

        this.mLaunchers = launchers;
    }

    public String getSpacecraft() {

        return mSpacecraft;
    }

    public void setSpacecraft(String spacecraft) {

        this.mSpacecraft = spacecraft;
    }

    public String getParent() {

        return mParent;
    }

    public void setParent(String parent) {

        this.mParent = parent;
    }

    public String getLaunchLibraryUrl() {

        return mLaunchLibraryUrl;
    }

    public void setLaunchLibraryUrl(String launchLibraryUrl) {

        this.mLaunchLibraryUrl = launchLibraryUrl;
    }

    public int getTotalLaunchCount() {

        return mTotalLaunchCount;
    }

    public void setTotalLaunchCount(int totalLaunchCount) {

        this.mTotalLaunchCount = totalLaunchCount;
    }

    public int getSuccessfulLaunches() {

        return mSuccessfulLaunches;
    }

    public void setSuccessfulLaunches(int successfulLaunches) {

        this.mSuccessfulLaunches = successfulLaunches;
    }

    public int getConsecutiveSuccessfulLaunches() {

        return mConsecutiveSuccessfulLaunches;
    }

    public void setConsecutiveSuccessfulLaunches(int consecutiveSuccessfulLaunches) {

        this.mConsecutiveSuccessfulLaunches = consecutiveSuccessfulLaunches;
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

    public int getSuccessfulLandings() {

        return mSuccessfulLandings;
    }

    public void setSuccessfulLandings(int successfulLandings) {

        this.mSuccessfulLandings = successfulLandings;
    }

    public int getFailedLandings() {

        return mFailedLandings;
    }

    public void setFailedLandings(int failedLandings) {

        this.mFailedLandings = failedLandings;
    }

    public int getAttemptedLandings() {

        return mAttemptedLandings;
    }

    public void setAttemptedLandings(int attemptedLandings) {

        this.mAttemptedLandings = attemptedLandings;
    }

    public int getConsecutiveSuccessfulLandings() {

        return mConsecutiveSuccessfulLandings;
    }

    public void setConsecutiveSuccessfulLandings(int consecutiveSuccessfulLandings) {

        this.mConsecutiveSuccessfulLandings = consecutiveSuccessfulLandings;
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

    public String getLogoUrl() {

        return mLogoUrl;
    }

    public void setLogoUrl(String logoUrl) {

        this.mLogoUrl = logoUrl;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }

    public String getNationUrl() {

        return mNationUrl;
    }

    public void setNationUrl(String nationUrl) {

        this.mNationUrl = nationUrl;
    }

    public List<LauncherConfig> getLauncherList() {

        return mLauncherList;
    }

    public void setLauncherList(List<LauncherConfig> launcherList) {

        this.mLauncherList = launcherList;
    }

    public List<SpacecraftConfiguration> getSpacecraftList() {

        return mSpacecraftList;
    }

    public void setSpacecraftList(List<SpacecraftConfiguration> spacecraftList) {

        this.mSpacecraftList = spacecraftList;
    }
}
