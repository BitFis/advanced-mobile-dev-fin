package ch.amk.exercise3.api.service;

import com.android.volley.toolbox.RequestFuture;

import org.apache.commons.lang3.NotImplementedException;

import javax.inject.Inject;

import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;

public class FeedbackService {

    @Inject
    public FeedbackService() { }

    public RequestFuture<Feedback> get(int id) {
        throw new NotImplementedException("not implemented yet");
    }

    public RequestFuture<Feedbacks> getAll(int page) {
        throw new NotImplementedException("not implemented yet");
    }

    public RequestFuture<Feedbacks> getAll() {
        return this.getAll(0);
    }

    public void delete(int id) {
        throw new NotImplementedException("not implemented yet");
    }

    /**
     * Creates a new feedback entry if the id is <= 0 otherwise
     * will update the feedback
     * @param feedback
     * @return
     */
    public RequestFuture<Feedback> save(Feedback feedback) {
        throw new NotImplementedException("not implemented yet");
    }
}
