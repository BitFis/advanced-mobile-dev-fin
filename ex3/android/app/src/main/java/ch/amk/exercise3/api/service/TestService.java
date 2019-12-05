package ch.amk.exercise3.api.service;

import javax.inject.Inject;

public class TestService {
    @Inject
    public TestService() { }

    public String getText() {
        return "Production App";
    }
}
