package ch.amk.exercise3.api.service;

import org.apache.commons.lang3.NotImplementedException;

import javax.inject.Inject;

import ch.amk.exercise3.api.models.Feedback;
import ch.amk.exercise3.api.models.Feedbacks;

public class FeedbackService {

    @Inject
    public FeedbackService() { }

    public Feedback get(int id) {
        throw new NotImplementedException("not implemented yet");
    }

    public Feedbacks getAll(int page) {
        throw new NotImplementedException("not implemented yet");
    }

    public Feedbacks getAll() {
        return this.getAll(0);
    }

    public void delete(int id) {
        throw new NotImplementedException("not implemented yet");
    }

    public void save(Feedback feedback) {
        throw new NotImplementedException("not implemented yet");
    }

    public void update(int id) {
        throw new NotImplementedException("not implemented yet");
    }

}
