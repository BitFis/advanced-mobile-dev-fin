package ch.amk.exercise1;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import ch.amk.exercise1.models.Post;
import ch.amk.exercise1.utils.ResourceHelper;

@RunWith(AndroidJUnit4.class)
public class PostItemInstrumentedTest {


    @Rule
    public ActivityTestRule<PostItemActivity> activityRule = new ActivityTestRule<>(
            PostItemActivity.class,
            true,
            false);

    private Context ctx;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowPostItem() throws IOException {
        Post post = new Post();
        post.Author = "Hans";
        post.DateCreated = Date.valueOf("2019-12-30");
        post.Body = ResourceHelper.loadAsset("lorem_lipsum.txt");
        post.ID = 12;
        post.Url = "https://google.com";
        post.Title = "random Title";

        Intent intent = new Intent();
        intent.putExtra(PostItemActivity.POST_ATTRIBUTE, (Parcelable) post);
        this.activityRule.launchActivity(intent);
    }

}
