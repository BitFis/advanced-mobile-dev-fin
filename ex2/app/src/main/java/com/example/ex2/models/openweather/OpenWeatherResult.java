
package com.example.ex2.models.openweather;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OpenWeatherResult implements Parcelable
{

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("coord")
    @Expose
    private Coord coord;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("base")
    @Expose
    private String base;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("main")
    @Expose
    private Main main;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("wind")
    @Expose
    private Wind wind;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("dt")
    @Expose
    private Integer dt;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("sys")
    @Expose
    private Sys sys;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    private Integer id;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("cod")
    @Expose
    private Integer cod;
    /**
     *
     * (Required)
     *
     */
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    public final static Parcelable.Creator<OpenWeatherResult> CREATOR = new Creator<OpenWeatherResult>() {

        @SuppressWarnings({
            "unchecked"
        })
        public OpenWeatherResult createFromParcel(Parcel in) {
            return new OpenWeatherResult(in);
        }

        public OpenWeatherResult[] newArray(int size) {
            return (new OpenWeatherResult[size]);
        }

    }
    ;

    protected OpenWeatherResult(Parcel in) {
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        in.readList(this.weather, (com.example.ex2.models.openweather.Weather.class.getClassLoader()));
        this.base = ((String) in.readValue((String.class.getClassLoader())));
        this.main = ((Main) in.readValue((Main.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.dt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.cod = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.timezone = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public OpenWeatherResult() {
    }

    /**
     * 
     * (Required)
     * 
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public OpenWeatherResult withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public OpenWeatherResult withWeather(List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getBase() {
        return base;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setBase(String base) {
        this.base = base;
    }

    public OpenWeatherResult withBase(String base) {
        this.base = base;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Main getMain() {
        return main;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setMain(Main main) {
        this.main = main;
    }

    public OpenWeatherResult withMain(Main main) {
        this.main = main;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public OpenWeatherResult withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public OpenWeatherResult withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Integer getDt() {
        return dt;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public OpenWeatherResult withDt(Integer dt) {
        this.dt = dt;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public OpenWeatherResult withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public OpenWeatherResult withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    public OpenWeatherResult withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public OpenWeatherResult withCod(Integer cod) {
        this.cod = cod;
        return this;
    }


    /**
     *
     * (Required)
     *
     */
    public Integer getTimezone() {
        return timezone;
    }

    /**
     *
     * (Required)
     *
     */
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public OpenWeatherResult withTimezone(Integer timezone) {
        this.timezone = timezone;
        return this;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("coord", coord).append("weather", weather).append("base", base).append("main", main).append("wind", wind).append("clouds", clouds).append("dt", dt).append("sys", sys).append("id", id).append("name", name).append("cod", cod).append("timezone", timezone).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(timezone).append(dt).append(coord).append(weather).append(name).append(cod).append(main).append(clouds).append(id).append(sys).append(base).append(wind).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OpenWeatherResult) == false) {
            return false;
        }
        OpenWeatherResult rhs = ((OpenWeatherResult) other);
        return new EqualsBuilder().append(timezone, rhs.timezone).append(dt, rhs.dt).append(coord, rhs.coord).append(weather, rhs.weather).append(name, rhs.name).append(cod, rhs.cod).append(main, rhs.main).append(clouds, rhs.clouds).append(id, rhs.id).append(sys, rhs.sys).append(base, rhs.base).append(wind, rhs.wind).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(coord);
        dest.writeList(weather);
        dest.writeValue(base);
        dest.writeValue(main);
        dest.writeValue(wind);
        dest.writeValue(clouds);
        dest.writeValue(dt);
        dest.writeValue(sys);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(cod);
        dest.writeValue(timezone);
    }

    public int describeContents() {
        return  0;
    }

}
