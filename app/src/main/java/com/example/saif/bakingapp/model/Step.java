
package com.example.saif.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Table(name = "Steps")
public class Step extends Model implements Parcelable
{

    @Column(name = "Step_id")
    @SerializedName("id")
    @Expose
    private Long id;

    @Column(name = "Short_Description")
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;

    @Column(name = "Description")
    @SerializedName("description")
    @Expose
    private String description;

    @Column(name = "Video_Url")
    @SerializedName("videoURL")
    @Expose
    private String videoURL;

    @Column(name = "Thumbnail_Url")
    @SerializedName("ThumbnailURL")
    @Expose
    private String thumbnailURL;

    @Column(name = "Recipe" )
    public Recipe recipe;

    public Step (){
        super();
    }
    public final static Parcelable.Creator<Step> CREATOR = new Creator<Step>() {
        @SuppressWarnings({
            "unchecked"
        })
        public Step createFromParcel(Parcel in) {
            Step instance = new Step();
            instance.id = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.videoURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.thumbnailURL = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Step[] newArray(int size) {
            return (new Step[size]);
        }

    }
    ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(shortDescription);
        dest.writeValue(description);
        dest.writeValue(videoURL);
        dest.writeValue(thumbnailURL);
    }

    public int describeContents() {
        return  0;
    }

}
