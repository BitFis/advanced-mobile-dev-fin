package ch.amk.exercise4.mqtt;

import android.app.Application;

// tag::extendedModules[]
import ch.amk.exercise4.mqtt.client.MqttModule;
// end::extendedModules[]

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {
    AndroidInjectionModule.class, // <1>
    CoreActivityModule.class, // <2>
        /** tag::description[]
<1> Include the `AndroidInjectionModule`, it provides the `DispatchingAndroidInjector<Object>` used by the `CoreApplication`
<2> Include dependent Module. Any Module providing implementations can be set here.
            end::description[] */
    // tag::extendedModules[]
    MqttModule.class,
    // end::extendedModules[]
})
public interface CoreComponent extends AndroidInjector<CoreApplication> {
    @Component.Builder // <3>
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        CoreComponent build();
    }
    /** tag::description[]
<3> The builder is needed, so in every Module, the Application context can be injected. This
     is really helpful and therefor great to have always present.
        end::description[] */
}
