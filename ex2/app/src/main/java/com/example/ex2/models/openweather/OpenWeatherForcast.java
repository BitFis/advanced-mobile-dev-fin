
package com.example.ex2.models.openweather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.ex2.modules.TimeModule;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Forcast Schema
 * <p>
 *
 *
 */
public class OpenWeatherForcast implements Parcelable
{

    /**
     * The Cod Schema
     * <p>
     *
     * (Required)
     *
     */
    @SerializedName("cod")
    @Expose
    private String cod = "";
    /**
     * The Message Schema
     * <p>
     *
     * (Required)
     *
     */
    @SerializedName("message")
    @Expose
    private Integer message = 0;
    /**
     * The Cnt Schema
     * <p>
     *
     * (Required)
     *
     */
    @SerializedName("cnt")
    @Expose
    private Integer cnt = 0;
    /**
     * The List Schema
     * <p>
     *
     * (Required)
     *
     */
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.ex2.models.openweather.List> list = null;
    /**
     * The City Schema
     * <p>
     *
     * (Required)
     *
     */
    @SerializedName("city")
    @Expose
    private City city;
    public final static Parcelable.Creator<OpenWeatherForcast> CREATOR = new Creator<OpenWeatherForcast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OpenWeatherForcast createFromParcel(Parcel in) {
            return new OpenWeatherForcast(in);
        }

        public OpenWeatherForcast[] newArray(int size) {
            return (new OpenWeatherForcast[size]);
        }

    }
            ;

    protected OpenWeatherForcast(Parcel in) {
        this.cod = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cnt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.list, (com.example.ex2.models.openweather.List.class.getClassLoader()));
        this.city = ((City) in.readValue((City.class.getClassLoader())));
    }

    public OpenWeatherForcast() {
    }

    /**
     * The Cod Schema
     * <p>
     *
     * (Required)
     *
     */
    public String getCod() {
        return cod;
    }

    /**
     * The Cod Schema
     * <p>
     *
     * (Required)
     *
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    public OpenWeatherForcast withCod(String cod) {
        this.cod = cod;
        return this;
    }

    /**
     * The Message Schema
     * <p>
     *
     * (Required)
     *
     */
    public Integer getMessage() {
        return message;
    }

    /**
     * The Message Schema
     * <p>
     *
     * (Required)
     *
     */
    public void setMessage(Integer message) {
        this.message = message;
    }

    public OpenWeatherForcast withMessage(Integer message) {
        this.message = message;
        return this;
    }

    /**
     * The Cnt Schema
     * <p>
     *
     * (Required)
     *
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * The Cnt Schema
     * <p>
     *
     * (Required)
     *
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public OpenWeatherForcast withCnt(Integer cnt) {
        this.cnt = cnt;
        return this;
    }

    /**
     * The List Schema
     * <p>
     *
     * (Required)
     *
     */
    public java.util.List<com.example.ex2.models.openweather.List> getList() {
        return list;
    }

    /**
     * The List Schema
     * <p>
     *
     * (Required)
     *
     */
    public void setList(java.util.List<com.example.ex2.models.openweather.List> list) {
        this.list = list;
    }

    public OpenWeatherForcast withList(java.util.List<com.example.ex2.models.openweather.List> list) {
        this.list = list;
        return this;
    }

    /**
     * The City Schema
     * <p>
     *
     * (Required)
     *
     */
    public City getCity() {
        return city;
    }

    /**
     * The City Schema
     * <p>
     *
     * (Required)
     *
     */
    public void setCity(City city) {
        this.city = city;
    }

    public OpenWeatherForcast withCity(City city) {
        this.city = city;
        return this;
    }

    public Map<String, java.util.List<List>> groupByDay() {
        DateTimeFormatter formatter = new TimeModule().provideDate();
        return this.getList().stream()
                .collect(Collectors.groupingBy(
                        forecast -> {
                            return formatter.format(Instant.ofEpochSecond(forecast.getDt()));
                        }
                ));
    }

    public Map<String, List> groupByDayMerge(BiFunction<java.util.List<List>, String, List> mergeFunction) {
        final Map<String, List> map = new HashMap<>();
        this.groupByDay().forEach((date, lists) -> {
            map.put(date, mergeFunction.apply(lists, date));
        });
        return map;
    }

    /**
     * At the moment only merge daly temp
     * @param list
     * @param date
     * @return
     */
    public static List mergeAvrages(java.util.List<List> list, String date) {
        DoubleSummaryStatistics temperature = new DoubleSummaryStatistics();
        list.forEach(item -> {
            temperature.accept(item.getMain().getTempMax());
            temperature.accept(item.getMain().getTempMin());
            temperature.accept(item.getMain().getTemp());
        });
        return new List()
            .withMain(
                new Main()
                    .withTemp(temperature.getAverage())
                    .withTempMax(temperature.getMax())
                    .withTempMin(temperature.getMin())
            );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cod", cod).append("message", message).append("cnt", cnt).append("list", list).append("city", city).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cnt).append(cod).append(message).append(list).append(city).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OpenWeatherForcast) == false) {
            return false;
        }
        OpenWeatherForcast rhs = ((OpenWeatherForcast) other);
        return new EqualsBuilder().append(cnt, rhs.cnt).append(cod, rhs.cod).append(message, rhs.message).append(list, rhs.list).append(city, rhs.city).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
        dest.writeValue(city);
    }

    public int describeContents() {
        return  0;
    }

}
