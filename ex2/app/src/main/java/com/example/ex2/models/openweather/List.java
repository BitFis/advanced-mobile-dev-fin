
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
 * The Items Schema
 * <p>
 * 
 * 
 */
public class List implements Parcelable
{

    /**
     * The Dt Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("dt")
    @Expose
    private Integer dt = 0;
    /**
     * The Main Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("main")
    @Expose
    private Main main;
    /**
     * The Weather Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    /**
     * The Clouds Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    /**
     * The Wind Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("wind")
    @Expose
    private Wind wind;
    /**
     * The Sys Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("sys")
    @Expose
    private Sys sys;
    /**
     * The Dt_txt Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt = "";
    public final static Parcelable.Creator<List> CREATOR = new Creator<List>() {


        @SuppressWarnings({
            "unchecked"
        })
        public List createFromParcel(Parcel in) {
            return new List(in);
        }

        public List[] newArray(int size) {
            return (new List[size]);
        }

    }
    ;

    protected List(Parcel in) {
        this.dt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.main = ((Main) in.readValue((Main.class.getClassLoader())));
        in.readList(this.weather, (com.example.ex2.models.openweather.Weather.class.getClassLoader()));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.dtTxt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public List() {
    }

    /**
     * The Dt Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getDt() {
        return dt;
    }

    /**
     * The Dt Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public List withDt(Integer dt) {
        this.dt = dt;
        return this;
    }

    /**
     * The Main Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Main getMain() {
        return main;
    }

    /**
     * The Main Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setMain(Main main) {
        this.main = main;
    }

    public List withMain(Main main) {
        this.main = main;
        return this;
    }

    /**
     * The Weather Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * The Weather Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public List withWeather(java.util.List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    /**
     * The Clouds Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * The Clouds Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public List withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    /**
     * The Wind Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * The Wind Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    /**
     * The Sys Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * The Sys Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    /**
     * The Dt_txt Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public String getDtTxt() {
        return dtTxt;
    }

    /**
     * The Dt_txt Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public List withDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dt", dt).append("main", main).append("weather", weather).append("clouds", clouds).append("wind", wind).append("sys", sys).append("dtTxt", dtTxt).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(dt).append(dtTxt).append(weather).append(main).append(clouds).append(sys).append(wind).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof List) == false) {
            return false;
        }
        List rhs = ((List) other);
        return new EqualsBuilder().append(dt, rhs.dt).append(dtTxt, rhs.dtTxt).append(weather, rhs.weather).append(main, rhs.main).append(clouds, rhs.clouds).append(sys, rhs.sys).append(wind, rhs.wind).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dt);
        dest.writeValue(main);
        dest.writeList(weather);
        dest.writeValue(clouds);
        dest.writeValue(wind);
        dest.writeValue(sys);
        dest.writeValue(dtTxt);
    }

    public int describeContents() {
        return  0;
    }

}
