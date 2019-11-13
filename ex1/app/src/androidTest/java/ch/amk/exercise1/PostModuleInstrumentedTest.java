package ch.amk.exercise1;

import android.os.Parcel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.time.Instant;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import ch.amk.exercise1.models.Post;

@RunWith(AndroidJUnit4.class)
public class PostModuleInstrumentedTest {

    @Test
    public void testReadWriteParcel() {
        String author = "Hans";

        Post postSource = new Post();

        postSource.ID = 12;
        postSource.Author = "Hansi";
        postSource.Body = "body";
        postSource.DateCreated = Date.from(Instant.now());
        postSource.Title = "Title";
        postSource.Url = "http://randome.com";

        Parcel parcel = Parcel.obtain();
        postSource.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        Post postRes = Post.CREATOR.createFromParcel(parcel);

        Assert.assertEquals(postRes.ID, postSource.ID);
        Assert.assertEquals(postRes.Author, postSource.Author);
        Assert.assertEquals(postRes.Body, postSource.Body);
        Assert.assertEquals(postRes.Title, postSource.Title);
        Assert.assertEquals(postRes.Url, postSource.Url);
        Assert.assertEquals(postRes.DateCreated, postSource.DateCreated);

    }

}
