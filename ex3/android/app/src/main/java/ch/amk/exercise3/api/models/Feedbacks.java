
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
 * The Root Schema
 * <p>
 * 
 * 
 */
public class Feedbacks implements Parcelable
{

    /**
     * The _links Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("_links")
    @Expose
    private Links links;
    /**
     * The _embedded Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    /**
     * The Page_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("page_count")
    @Expose
    private Integer pageCount = 0;
    /**
     * The Page_size Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("page_size")
    @Expose
    private Integer pageSize = 0;
    /**
     * The Total_items Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("total_items")
    @Expose
    private Integer totalItems = 0;
    /**
     * The Page Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("page")
    @Expose
    private Integer page = 0;
    public final static Parcelable.Creator<Feedbacks> CREATOR = new Creator<Feedbacks>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Feedbacks createFromParcel(Parcel in) {
            return new Feedbacks(in);
        }

        public Feedbacks[] newArray(int size) {
            return (new Feedbacks[size]);
        }

    }
    ;

    protected Feedbacks(Parcel in) {
        this.links = ((Links) in.readValue((Links.class.getClassLoader())));
        this.embedded = ((Embedded) in.readValue((Embedded.class.getClassLoader())));
        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pageSize = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalItems = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Feedbacks() {
    }

    /**
     * The _links Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Links getLinks() {
        return links;
    }

    /**
     * The _links Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setLinks(Links links) {
        this.links = links;
    }

    public Feedbacks withLinks(Links links) {
        this.links = links;
        return this;
    }

    /**
     * The _embedded Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Embedded getEmbedded() {
        return embedded;
    }

    /**
     * The _embedded Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Feedbacks withEmbedded(Embedded embedded) {
        this.embedded = embedded;
        return this;
    }

    /**
     * The Page_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * The Page_count Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Feedbacks withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * The Page_size Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * The Page_size Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Feedbacks withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * The Total_items Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * The Total_items Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Feedbacks withTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    /**
     * The Page Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public Integer getPage() {
        return page;
    }

    /**
     * The Page Schema
     * <p>
     * 
     * (Required)
     * 
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public Feedbacks withPage(Integer page) {
        this.page = page;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("links", links).append("embedded", embedded).append("pageCount", pageCount).append("pageSize", pageSize).append("totalItems", totalItems).append("page", page).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pageCount).append(totalItems).append(pageSize).append(links).append(page).append(embedded).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Feedbacks) == false) {
            return false;
        }
        Feedbacks rhs = ((Feedbacks) other);
        return new EqualsBuilder().append(pageCount, rhs.pageCount).append(totalItems, rhs.totalItems).append(pageSize, rhs.pageSize).append(links, rhs.links).append(page, rhs.page).append(embedded, rhs.embedded).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(links);
        dest.writeValue(embedded);
        dest.writeValue(pageCount);
        dest.writeValue(pageSize);
        dest.writeValue(totalItems);
        dest.writeValue(page);
    }

    public int describeContents() {
        return  0;
    }

}
