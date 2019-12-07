package ch.amk.exercise3.api;

import android.app.Application;

import ch.amk.exercise3.api.app.ConfigModule;
import ch.amk.exercise3.api.app.GsonModule;
import ch.amk.exercise3.api.app.MainActivityModule;
import ch.amk.exercise3.api.app.VolleyModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

@Component(modules = {
        AndroidInjectionModule.class,
        ConfigModule.class,
        VolleyModule.class,
        GsonModule.class,
        MainActivityModule.class,
})
public interface CoreComponent extends AndroidInjector<CoreApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        CoreComponent build();
    }
}
