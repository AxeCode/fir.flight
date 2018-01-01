package io.github.ryanhoo.firFlight.data.model.impl;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import io.github.ryanhoo.firFlight.data.model.IMessageContent;
import io.github.ryanhoo.firFlight.data.model.Release;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/19/16
 * Time: 8:12 PM
 * Desc: ReleaseMessageContent
 */
public class ReleaseMessageContent implements IMessageContent {

    public ReleaseMessageContent() {
        // Empty Constructor
    }

    protected ReleaseMessageContent(Parcel in) {
        readFromParcel(in);
    }

    @SerializedName("user")
    private String user;

    @SerializedName("release")
    private Release release;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.user);
        dest.writeParcelable(this.release, flags);
    }

    private void readFromParcel(Parcel in) {
        this.user = in.readString();
        this.release = in.readParcelable(Release.class.getClassLoader());
    }

    public static final Creator<ReleaseMessageContent> CREATOR = new Creator<ReleaseMessageContent>() {
        @Override
        public ReleaseMessageContent createFromParcel(Parcel source) {
            return new ReleaseMessageContent(source);
        }

        @Override
        public ReleaseMessageContent[] newArray(int size) {
            return new ReleaseMessageContent[size];
        }
    };
}
