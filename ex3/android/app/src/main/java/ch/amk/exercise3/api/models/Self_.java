
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
public class Self_ implements Parcelable
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
    public final static Parcelable.Creator<Self_> CREATOR = new Creator<Self_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Self_ createFromParcel(Parcel in) {
            return new Self_(in);
        }

        public Self_[] newArray(int size) {
            return (new Self_[size]);
        }

    }
    ;

    protected Self_(Parcel in) {
        this.href = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Self_() {
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

    public Self_ withHref(String href) {
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
        if ((other instanceof Self_) == false) {
            return false;
        }
        Self_ rhs = ((Self_) other);
        return new EqualsBuilder().append(href, rhs.href).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(href);
    }

    public int describeContents() {
        return  0;
    }

}
