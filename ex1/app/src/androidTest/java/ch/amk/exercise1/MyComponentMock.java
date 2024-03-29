package ch.amk.exercise1;

import android.app.Application;

import javax.inject.Singleton;

import ch.amk.exercise1.modules.Gson;
import ch.amk.exercise1.modules.MainMock;
import ch.amk.exercise1.spinner.CitySelectorInstrumentedTest;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        MainMock.class,
        Gson.class
})
public interface MyComponentMock extends MyComponent {
    void inject(PostActivityInstrumentedTest postActivityInstrumentedTest);
    void inject(CitySelectorInstrumentedTest citySelectorInstrumentedTest);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyComponentMock.Builder application(Application application);

        MyComponentMock build();
    }
}
