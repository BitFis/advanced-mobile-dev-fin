
package ch.amk.exercise3.api.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The _embedded Schema
 * <p>
 * 
 * 
 */
public class Embedded implements Parcelable
{

    /**
     * The Feedback Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("feedback")
    @Expose
    private List<Feedback> feedback = null;
    public final static Parcelable.Creator<Embedded> CREATOR = new Creator<Embedded>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Embedded createFromParcel(Parcel in) {
            return new Embedded(in);
        }

        public Embedded[] newArray(int size) {
            return (new Embedded[size]);
        }

    }
    ;

    protected Embedded(Parcel in) {
        in.readList(this.feedback, (ch.amk.exercise3.api.models.Feedback.class.getClassLoader()));
    }

    public Embedded() {
    }

    /**
     * The Feedback Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public List<Feedback> getFeedback() {
        return feedback;
    }

    /**
     * The Feedback Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public Embedded withFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("feedback", feedback).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(feedback).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Embedded) == false) {
            return false;
        }
        Embedded rhs = ((Embedded) other);
        return new EqualsBuilder().append(feedback, rhs.feedback).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(feedback);
    }

    public int describeContents() {
        return  0;
    }

}
