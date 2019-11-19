package com.example.ex2;

import android.app.Application;

import com.example.ex2.modules.GsonModule;
import com.example.ex2.modules.VolleyModule;
import com.example.ex2.modules.VolleyModuleMock;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
    GsonModule.class,
    VolleyModuleMock.class
})
public interface CoreComponentMock extends CoreComponent {
    void inject(MapsActivityInstrumentedTest test);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CoreComponentMock.Builder application(Application application);

        CoreComponentMock build();
    }

}
