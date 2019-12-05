package ch.amk.exercise3.api.models;

import com.google.common.base.Utf8;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.CharSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackTest {

    private Gson gson;
    private Feedback feedback;

    @Before
    public void setup() {
        this.gson = new GsonBuilder().create();

        this.feedback = new Feedback()
            .withId(1)
            .withName("Hans Example")
            .withValue("This is a test feedback for later")
            .withLocation("rovaniemi, fi")
            .withLinks(
                new Links_().withSelf(
                        new Self_().withHref("http://localhost:8080/feedback/1")
                ));
    }

    @Test
    public void testLoadFeedbackCollection() throws IOException {
        URL url = Resources.getResource("feedback_list.json");
        String input = Resources.toString(url, StandardCharsets.UTF_8);

        Feedbacks feedbacks = this.gson.fromJson(input, Feedbacks.class);

        assertThat(feedbacks.getTotalItems(), is(1));
        assertThat(feedbacks.getEmbedded().getFeedback(), hasItem(feedback));
    }

    @Test
    public void testLoadSingleFeedback() throws IOException {
        URL url = Resources.getResource("feedback_single.json");
        String input = Resources.toString(url, StandardCharsets.UTF_8);

        Feedback read = this.gson.fromJson(input, Feedback.class);

        assertThat(read, is(this.feedback));
    }

}
