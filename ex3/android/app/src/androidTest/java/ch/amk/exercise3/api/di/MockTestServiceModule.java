package ch.amk.exercise3.api.di;

import static org.mockito.Mockito.mock;

import javax.inject.Singleton;

import ch.amk.exercise3.api.service.TestService;
import dagger.Module;
import dagger.Provides;

@Module
public class MockTestServiceModule {

    @Singleton
    @Provides
    public static TestService provideTestService() {
        return mock(TestService.class);
    }
}
