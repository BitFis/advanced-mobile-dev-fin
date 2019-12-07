package ch.amk.exercise3.api.service;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ch.amk.exercise2.utils.MockSuccessResponse;
import ch.amk.exercise2.utils.RequestQueueMockBuilder;
import ch.amk.exercise3.api.app.ConfigModule;
import ch.amk.exercise3.api.app.GsonModule;
import ch.amk.exercise3.api.models.Feedbacks;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FeedbackServiceTest {

    private FeedbackService feedbackService;

    @Mock
    Network mNetwork;

    @Captor
    ArgumentCaptor<Request> requestArgumentCaptor;

    @Before
    public void setup() {
        initMocks(this);

        this.feedbackService = new FeedbackService(
                "http://localhost:8080/api/v2/",
                new RequestQueueMockBuilder().network(this.mNetwork).create(),
                new GsonModule().provideGson());
    }

    @Test
    public void testBackendUrl() throws VolleyError, IOException, ExecutionException, InterruptedException {
        when(this.mNetwork.performRequest(this.requestArgumentCaptor.capture()))
                .thenReturn(MockSuccessResponse.fromResource("feedback_list.json"));

        this.feedbackService.getAll().get();
        this.feedbackService.getAll().get();

        assertEquals("http://localhost:8080/api/v2/feedback", this.requestArgumentCaptor.getAllValues().get(0).getUrl());
        assertEquals("http://localhost:8080/api/v2/feedback", this.requestArgumentCaptor.getAllValues().get(1).getUrl());
    }

    @Test
    public void testGetAllFeedbacks() throws ExecutionException, InterruptedException, VolleyError, IOException {
        when(this.mNetwork.performRequest(this.requestArgumentCaptor.capture()))
                .thenReturn(MockSuccessResponse.fromResource("feedback_list.json"));

        Feedbacks feedbacks = this.feedbackService.getAll().get();

        assertEquals(1, feedbacks.getEmbedded().getFeedback().size());
        assertEquals("Hans Example", feedbacks.getEmbedded().getFeedback().get(0).getName());
    }
}
