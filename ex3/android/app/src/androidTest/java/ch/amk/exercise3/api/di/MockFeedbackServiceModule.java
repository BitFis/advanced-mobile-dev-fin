package ch.amk.exercise3.api.di;

import static org.mockito.Mockito.mock;

import javax.inject.Singleton;

import ch.amk.exercise3.api.service.FeedbackService;
import dagger.Module;
import dagger.Provides;

@Module
public class MockFeedbackServiceModule {

    @Singleton
    @Provides
    public static FeedbackService provideFeedbackService() {
        return mock(FeedbackService.class);
    }
}
