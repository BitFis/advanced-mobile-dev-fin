
package ch.amk.exercise1.models.openweather;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenWeather {

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

}
