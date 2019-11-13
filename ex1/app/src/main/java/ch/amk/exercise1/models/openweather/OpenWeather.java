
package ch.amk.exercise1.models.openweather;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OpenWeather implements Parcelable
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
    private String base = "cmc stations";
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
    private Integer dt = 1449303600;
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
    private Integer id = 2643743;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    private String name = "London";
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("cod")
    @Expose
    private Integer cod = 200;
    public final static Parcelable.Creator<OpenWeather> CREATOR = new Creator<OpenWeather>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OpenWeather createFromParcel(Parcel in) {
            return new OpenWeather(in);
        }

        public OpenWeather[] newArray(int size) {
            return (new OpenWeather[size]);
        }

    }
    ;

    protected OpenWeather(Parcel in) {
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        in.readList(this.weather, (ch.amk.exercise1.models.openweather.Weather.class.getClassLoader()));
        this.base = ((String) in.readValue((String.class.getClassLoader())));
        this.main = ((Main) in.readValue((Main.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.dt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.cod = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public OpenWeather() {
    }

    /**
     * 
     * @param dt
     * @param coord
     * @param weather
     * @param name
     * @param cod
     * @param main
     * @param clouds
     * @param id
     * @param sys
     * @param base
     * @param wind
     */
    public OpenWeather(Coord coord, List<Weather> weather, String base, Main main, Wind wind, Clouds clouds, Integer dt, Sys sys, Integer id, String name, Integer cod) {
        super();
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
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

    public OpenWeather withCoord(Coord coord) {
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

    public OpenWeather withWeather(List<Weather> weather) {
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

    public OpenWeather withBase(String base) {
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

    public OpenWeather withMain(Main main) {
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

    public OpenWeather withWind(Wind wind) {
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

    public OpenWeather withClouds(Clouds clouds) {
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

    public OpenWeather withDt(Integer dt) {
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

    public OpenWeather withSys(Sys sys) {
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

    public OpenWeather withId(Integer id) {
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

    public OpenWeather withName(String name) {
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

    public OpenWeather withCod(Integer cod) {
        this.cod = cod;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("coord", coord).append("weather", weather).append("base", base).append("main", main).append("wind", wind).append("clouds", clouds).append("dt", dt).append("sys", sys).append("id", id).append("name", name).append("cod", cod).toString();
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
    }

    public int describeContents() {
        return  0;
    }

}
