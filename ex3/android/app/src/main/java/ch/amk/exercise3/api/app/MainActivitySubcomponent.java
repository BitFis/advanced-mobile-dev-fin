package ch.amk.exercise3.api.app;

import ch.amk.exercise3.api.FeedbackFormActivity;
import ch.amk.exercise3.api.MainActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;

@Subcomponent(modules = {})
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    public interface Factory extends AndroidInjector.Factory<MainActivity> {}
}
