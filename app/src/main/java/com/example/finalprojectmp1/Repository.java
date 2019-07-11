package com.example.finalprojectmp1;

import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable {
   String name,owner_name,owner_avatar_url,languages_url,description;


    protected Repository(Parcel in) {
        name = in.readString();
        owner_name = in.readString();
        owner_avatar_url = in.readString();
        languages_url = in.readString();
        description = in.readString();
    }

    public Repository(String name, String owner_name, String owner_avatar_url, String languages_url, String description) {
        this.name = name;
        this.owner_name = owner_name;
        this.owner_avatar_url = owner_avatar_url;
        this.languages_url = languages_url;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_avatar_url() {
        return owner_avatar_url;
    }

    public void setOwner_avatar_url(String owner_avatar_url) {
        this.owner_avatar_url = owner_avatar_url;
    }

    public String getLanguages_url() {
        return languages_url;
    }

    public void setLanguages_url(String languages_url) {
        this.languages_url = languages_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(owner_name);
        parcel.writeString(owner_avatar_url);
        parcel.writeString(languages_url);
        parcel.writeString(description);
    }
}
