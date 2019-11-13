
package ch.amk.exercise1.models.openweather;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Weather implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id = 802;
    @SerializedName("main")
    @Expose
    private String main = "Clouds";
    @SerializedName("description")
    @Expose
    private String description = "scattered clouds";
    @SerializedName("icon")
    @Expose
    private String icon = "03d";
    public final static Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return (new Weather[size]);
        }

    }
    ;

    protected Weather(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.main = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Weather() {
    }

    /**
     * 
     * @param icon
     * @param description
     * @param main
     * @param id
     */
    public Weather(Integer id, String main, String description, String icon) {
        super();
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Weather withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Weather withMain(String main) {
        this.main = main;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Weather withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Weather withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("main", main).append("description", description).append("icon", icon).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(main);
        dest.writeValue(description);
        dest.writeValue(icon);
    }

    public int describeContents() {
        return  0;
    }

}
