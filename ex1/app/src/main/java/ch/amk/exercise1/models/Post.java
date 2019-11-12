package ch.amk.exercise1.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.Nullable;

final public class Post implements Parcelable, Serializable {

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

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return (Post)source.readSerializable();
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[0];
        }
    };

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;

        if (!(obj instanceof Post)) return false;
        if (obj.getClass() != this.getClass()) return false;

        Post post = (Post)obj;

        return Objects.equals(post.ID, this.ID) &&
                Objects.equals(post.Author, this.Author) &&
                Objects.equals(post.Body, this.Body) &&
                Objects.equals(post.DateCreated, this.DateCreated) &&
                Objects.equals(post.Title, this.Title) &&
                Objects.equals(post.Url, this.Url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.ID,
                this.Author,
                this.Body,
                this.DateCreated,
                this.Title,
                this.Url
        );
    }
}
