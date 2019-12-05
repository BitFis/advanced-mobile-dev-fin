
package ch.amk.exercise3.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The Items Schema
 * <p>
 * 
 * 
 */
public class Feedback implements Parcelable
{

    /**
     * The Id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    private Integer id = 0;
    /**
     * The Name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    private String name = "";
    /**
     * The Value Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("value")
    @Expose
    private String value = "";
    /**
     * The Location Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("location")
    @Expose
    private String location = "";
    /**
     * The _links Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("_links")
    @Expose
    private Links_ links;
    public final static Parcelable.Creator<Feedback> CREATOR = new Creator<Feedback>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Feedback createFromParcel(Parcel in) {
            return new Feedback(in);
        }

        public Feedback[] newArray(int size) {
            return (new Feedback[size]);
        }

    }
    ;

    protected Feedback(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.links = ((Links_) in.readValue((Links_.class.getClassLoader())));
    }

    public Feedback() {
    }

    /**
     * The Id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * The Id Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Feedback withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * The Name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * The Name Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    public Feedback withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The Value Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public String getValue() {
        return value;
    }

    /**
     * The Value Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }

    public Feedback withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * The Location Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public String getLocation() {
        return location;
    }

    /**
     * The Location Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public Feedback withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * The _links Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Links_ getLinks() {
        return links;
    }

    /**
     * The _links Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setLinks(Links_ links) {
        this.links = links;
    }

    public Feedback withLinks(Links_ links) {
        this.links = links;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("value", value).append("location", location).append("links", links).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(location).append(links).append(id).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Feedback) == false) {
            return false;
        }
        Feedback rhs = ((Feedback) other);
        return new EqualsBuilder().append(name, rhs.name).append(location, rhs.location).append(links, rhs.links).append(id, rhs.id).append(value, rhs.value).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(value);
        dest.writeValue(location);
        dest.writeValue(links);
    }

    public int describeContents() {
        return  0;
    }

}
