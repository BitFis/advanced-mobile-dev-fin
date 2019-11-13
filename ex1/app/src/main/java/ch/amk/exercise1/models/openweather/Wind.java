
package ch.amk.exercise1.models.openweather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Wind implements Parcelable
{

    @SerializedName("speed")
    @Expose
    private Double speed = 10.8D;
    @SerializedName("deg")
    @Expose
    private Integer deg = 220;
    @SerializedName("gust")
    @Expose
    private Double gust = 15.9D;
    public final static Parcelable.Creator<Wind> CREATOR = new Creator<Wind>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        public Wind[] newArray(int size) {
            return (new Wind[size]);
        }

    }
    ;

    protected Wind(Parcel in) {
        this.speed = ((Double) in.readValue((Double.class.getClassLoader())));
        this.deg = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.gust = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wind() {
    }

    /**
     * 
     * @param deg
     * @param speed
     * @param gust
     */
    public Wind(Double speed, Integer deg, Double gust) {
        super();
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Wind withSpeed(Double speed) {
        this.speed = speed;
        return this;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Wind withDeg(Integer deg) {
        this.deg = deg;
        return this;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
    }

    public Wind withGust(Double gust) {
        this.gust = gust;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("speed", speed).append("deg", deg).append("gust", gust).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(speed);
        dest.writeValue(deg);
        dest.writeValue(gust);
    }

    public int describeContents() {
        return  0;
    }

}
