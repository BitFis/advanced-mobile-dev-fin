
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
 * The Self Schema
 * <p>
 * 
 * 
 */
public class Self implements Parcelable
{

    /**
     * The Href Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("href")
    @Expose
    private String href = "";
    public final static Parcelable.Creator<Self> CREATOR = new Creator<Self>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Self createFromParcel(Parcel in) {
            return new Self(in);
        }

        public Self[] newArray(int size) {
            return (new Self[size]);
        }

    }
    ;

    protected Self(Parcel in) {
        this.href = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Self() {
    }

    /**
     * The Href Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public String getHref() {
        return href;
    }

    /**
     * The Href Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setHref(String href) {
        this.href = href;
    }

    public Self withHref(String href) {
        this.href = href;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("href", href).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(href).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Self) == false) {
            return false;
        }
        Self rhs = ((Self) other);
        return new EqualsBuilder().append(href, rhs.href).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(href);
    }

    public int describeContents() {
        return  0;
    }

}
