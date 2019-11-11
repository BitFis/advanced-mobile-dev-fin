package ch.amk.exercise1;

import android.app.Application;

import javax.inject.Singleton;

import ch.amk.exercise1.PostActivity;
import ch.amk.exercise1.modules.Main;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = Main.class)
public interface MyComponent {
    void inject(PostActivity postActivity);
    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        MyComponent build();
    }
}
