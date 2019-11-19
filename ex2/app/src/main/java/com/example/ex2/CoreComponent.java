package com.example.ex2;

import android.app.Application;

import com.example.ex2.modules.GsonModule;
import com.example.ex2.modules.VolleyModule;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {
        GsonModule.class,
        VolleyModule.class
})
public interface CoreComponent {
    void inject(CoreApplication app);
    void inject(MapsActivity mapsActivity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        CoreComponent build();
    }
}
