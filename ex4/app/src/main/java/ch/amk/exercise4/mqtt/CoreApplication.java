package ch.amk.exercise4.mqtt;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CoreApplication extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector; // <1>
    /** tag::description[]
<1> inject the injector for android
        end::description[] */

    @Override
    public void onCreate() {
        super.onCreate();
        this.createComponent();
    }

    /** tag::description[]
<2> create the component (use a separate function to make it more simple to override it in the instrumented test environment)
        end::description[] */

    protected void createComponent() {
        DaggerCoreComponent.builder() // <3>
                .application(this).build()
                .inject(this); // <4>
    }

    /** tag::description[]
<3> Build CoreComponent (This might throw an error till the project has build successfully)
<4> Inject itself, this will resolve all class attributes with the `@Inject` annotation
        end::description[] */

    @Override
    public AndroidInjector<Object> androidInjector() {
        return this.dispatchingAndroidInjector; // <5>
    }
    /** tag::description[]
<5> return the injector used by the android components
        end::description[] */
}
