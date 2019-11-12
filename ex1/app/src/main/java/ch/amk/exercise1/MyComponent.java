package ch.amk.exercise1;

import android.app.Application;

import javax.inject.Singleton;

import ch.amk.exercise1.PostActivity;
import ch.amk.exercise1.modules.Gson;
import ch.amk.exercise1.modules.Main;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {
        Main.class,
        Gson.class
})
public interface MyComponent {
    void inject(App app);

    void inject(PostActivity postActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        MyComponent build();
    }
}
