package ch.amk.exercise3.api.app;

import ch.amk.exercise3.api.FeedbackFormActivity;
import ch.amk.exercise3.api.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = MainActivitySubcomponent.class)
public interface MainActivityModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?>
        bindMainAndroidInjectorFactory(MainActivitySubcomponent.Factory factory);

    @ContributesAndroidInjector(modules = { })
    abstract FeedbackFormActivity contributeFeedbackFormActivityAndroidInjector();

}
