package it.rokettoapp.roketto.model;

import com.google.gson.annotations.SerializedName;

public class Agency {

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

    @SerializedName("image_url")
    private String mImageUrl;

    public Agency(String name, boolean featured, String type, String countryCode,
                  String abbreviation, String description, String administrator,
                  String foundingYear, String launchers, String spacecraft, String parent,
                  String imageUrl) {

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
        this.mImageUrl = imageUrl;
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

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.mImageUrl = imageUrl;
    }
}
