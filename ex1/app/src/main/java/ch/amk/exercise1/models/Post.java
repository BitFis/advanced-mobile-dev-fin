package ch.amk.exercise1.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

final public class Post {

    @SerializedName("id")
    long ID;

    @SerializedName("date")
    Date dateCreated;

    String title;
    String author;
    String url;
    String body;

}
