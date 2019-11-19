
package com.example.ex2.models.openweather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The City Schema
 * <p>
 * 
 * 
 */
public class City implements Parcelable
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
     * The Coord Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("coord")
    @Expose
    private Coord coord;
    /**
     * The Country Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("country")
    @Expose
    private String country = "";
    /**
     * The Timezone Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("timezone")
    @Expose
    private Integer timezone = 0;
    /**
     * The Sunrise Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise = 0;
    /**
     * The Sunset Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("sunset")
    @Expose
    private Integer sunset = 0;
    public final static Parcelable.Creator<City> CREATOR = new Creator<City>() {


        @SuppressWarnings({
            "unchecked"
        })
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    }
    ;

    protected City(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.timezone = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sunrise = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sunset = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public City() {
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

    public City withId(Integer id) {
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

    public City withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The Coord Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * The Coord Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public City withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    /**
     * The Country Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public String getCountry() {
        return country;
    }

    /**
     * The Country Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public City withCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * The Timezone Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     * The Timezone Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public City withTimezone(Integer timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     * The Sunrise Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getSunrise() {
        return sunrise;
    }

    /**
     * The Sunrise Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public City withSunrise(Integer sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    /**
     * The Sunset Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getSunset() {
        return sunset;
    }

    /**
     * The Sunset Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public City withSunset(Integer sunset) {
        this.sunset = sunset;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("coord", coord).append("country", country).append("timezone", timezone).append("sunrise", sunrise).append("sunset", sunset).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(country).append(coord).append(sunrise).append(timezone).append(sunset).append(name).append(id).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof City) == false) {
            return false;
        }
        City rhs = ((City) other);
        return new EqualsBuilder().append(country, rhs.country).append(coord, rhs.coord).append(sunrise, rhs.sunrise).append(timezone, rhs.timezone).append(sunset, rhs.sunset).append(name, rhs.name).append(id, rhs.id).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(coord);
        dest.writeValue(country);
        dest.writeValue(timezone);
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
    }

    public int describeContents() {
        return  0;
    }

}
