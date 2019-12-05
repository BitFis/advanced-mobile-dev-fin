
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
public class Links implements Parcelable
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
    private Self self;
    /**
     * The First Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("first")
    @Expose
    private First first;
    /**
     * The Last Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("last")
    @Expose
    private Last last;
    public final static Parcelable.Creator<Links> CREATOR = new Creator<Links>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        public Links[] newArray(int size) {
            return (new Links[size]);
        }

    }
    ;

    protected Links(Parcel in) {
        this.self = ((Self) in.readValue((Self.class.getClassLoader())));
        this.first = ((First) in.readValue((First.class.getClassLoader())));
        this.last = ((Last) in.readValue((Last.class.getClassLoader())));
    }

    public Links() {
    }

    /**
     * The Self Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Self getSelf() {
        return self;
    }

    /**
     * The Self Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setSelf(Self self) {
        this.self = self;
    }

    public Links withSelf(Self self) {
        this.self = self;
        return this;
    }

    /**
     * The First Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public First getFirst() {
        return first;
    }

    /**
     * The First Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setFirst(First first) {
        this.first = first;
    }

    public Links withFirst(First first) {
        this.first = first;
        return this;
    }

    /**
     * The Last Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Last getLast() {
        return last;
    }

    /**
     * The Last Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setLast(Last last) {
        this.last = last;
    }

    public Links withLast(Last last) {
        this.last = last;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("self", self).append("first", first).append("last", last).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(self).append(last).append(first).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Links) == false) {
            return false;
        }
        Links rhs = ((Links) other);
        return new EqualsBuilder().append(self, rhs.self).append(last, rhs.last).append(first, rhs.first).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(self);
        dest.writeValue(first);
        dest.writeValue(last);
    }

    public int describeContents() {
        return  0;
    }

}
