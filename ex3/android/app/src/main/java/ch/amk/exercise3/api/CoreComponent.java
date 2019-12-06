package ch.amk.exercise3.api;

import ch.amk.exercise3.api.app.MainActivityModule;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

@Component(modules = {
        AndroidInjectionModule.class,
        MainActivityModule.class
})
public interface CoreComponent extends AndroidInjector<CoreApplication> { }
