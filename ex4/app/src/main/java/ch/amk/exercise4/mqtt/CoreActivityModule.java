package ch.amk.exercise4.mqtt;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module // <1>
/** tag::description[]
<1> define it as a module
    end::description[] */
public interface CoreActivityModule { // <2>
    /** tag::description[]
<2> make it an interface
        end::description[] */

    @ContributesAndroidInjector(modules = { })
    abstract MainActivity contributeFeedbackFormActivityAndroidInjector(); // <3>
    /** tag::description[]
<3> provide the Activity class through an abstract function. The function name can be choosen freely
    but it needs to have the *@ContributesAndroidInjector* annotation.
        end::description[] */

    // tag::otherActivities[]

    // end::otherActivities[]

}
