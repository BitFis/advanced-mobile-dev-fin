
package com.example.ex2.models.openweather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Coord implements Parcelable
{

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("lon")
    @Expose
    private Double lon;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("lat")
    @Expose
    private Double lat;
    public final static Parcelable.Creator<Coord> CREATOR = new Creator<Coord>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        public Coord[] newArray(int size) {
            return (new Coord[size]);
        }

    }
    ;

    protected Coord(Parcel in) {
        this.lon = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Coord() {
    }

    /**
     * 
     * (Required)
     * 
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Coord withLon(Double lon) {
        this.lon = lon;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Coord withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("lon", lon).append("lat", lat).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lon).append(lat).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Coord) == false) {
            return false;
        }
        Coord rhs = ((Coord) other);
        return new EqualsBuilder().append(lon, rhs.lon).append(lat, rhs.lat).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lon);
        dest.writeValue(lat);
    }

    public int describeContents() {
        return  0;
    }

}
