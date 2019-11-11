package ch.amk.exercise1.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

final public class Post {

    @SerializedName("id")
    public long ID;

    @SerializedName("date")
    public Date DateCreated;

    @SerializedName("title")
    public String Title;
    @SerializedName("author")
    public String Author;
    @SerializedName("url")
    public String Url;
    @SerializedName("body")
    public String Body;

}
