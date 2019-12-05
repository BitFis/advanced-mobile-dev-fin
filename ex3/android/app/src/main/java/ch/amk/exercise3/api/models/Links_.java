
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
 * The _links Schema
 * <p>
 * 
 * 
 */
public class Links_ implements Parcelable
{

    /**
     * The Self Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("self")
    @Expose
    private Self_ self;
    public final static Parcelable.Creator<Links_> CREATOR = new Creator<Links_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Links_ createFromParcel(Parcel in) {
            return new Links_(in);
        }

        public Links_[] newArray(int size) {
            return (new Links_[size]);
        }

    }
    ;

    protected Links_(Parcel in) {
        this.self = ((Self_) in.readValue((Self_.class.getClassLoader())));
    }

    public Links_() {
    }

    /**
     * The Self Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Self_ getSelf() {
        return self;
    }

    /**
     * The Self Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setSelf(Self_ self) {
        this.self = self;
    }

    public Links_ withSelf(Self_ self) {
        this.self = self;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("self", self).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(self).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Links_) == false) {
            return false;
        }
        Links_ rhs = ((Links_) other);
        return new EqualsBuilder().append(self, rhs.self).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(self);
    }

    public int describeContents() {
        return  0;
    }

}
