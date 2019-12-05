package ch.amk.exercise3.api.di;

import javax.inject.Singleton;

import ch.amk.exercise3.api.ExampleInstrumentedTest;
import ch.amk.exercise3.api.app.MainActivityModule;
import ch.amk.exercise3.api.ui.FeedbackViewInstrumentedTest;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        MainActivityModule.class,

        // mocked modules
        MockFeedbackServiceModule.class,
})
public interface MockComponent extends AndroidInjector<MockApplication> {
    void inject(ExampleInstrumentedTest exampleInstrumentedTest);
    void inject(FeedbackViewInstrumentedTest feedbackViewInstrumentedTest);
}
