package com.example.finalprojectmp1;

import android.os.Parcel;
import android.os.Parcelable;

public class Owner implements Parcelable {
    String login_name,image_url,followers_url,following_url,organization_url,repos_url;

    public Owner(String login_name, String image_url, String followers_url, String following_url, String organization_url, String repos_url) {
        this.login_name = login_name;
        this.image_url = image_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.organization_url = organization_url;
        this.repos_url = repos_url;
    }

    protected Owner(Parcel in) {
        login_name = in.readString();
        image_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        organization_url = in.readString();
        repos_url = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getOrganization_url() {
        return organization_url;
    }

    public void setOrganization_url(String organization_url) {
        this.organization_url = organization_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login_name);
        parcel.writeString(image_url);
        parcel.writeString(followers_url);
        parcel.writeString(following_url);
        parcel.writeString(organization_url);
        parcel.writeString(repos_url);
    }
}
