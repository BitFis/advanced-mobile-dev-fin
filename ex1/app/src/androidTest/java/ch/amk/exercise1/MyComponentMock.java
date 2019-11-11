package ch.amk.exercise1;

import android.app.Application;

import javax.inject.Singleton;

import ch.amk.exercise1.modules.MainMock;
import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = MainMock.class)
public interface MyComponentMock extends MyComponent {
    void inject(PostActivityInstrumentedTest postActivityInstrumentedTest);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyComponentMock.Builder application(Application application);

        MyComponentMock build();
    }
}
