package com.example.ex2;

import android.app.Application;

public class CoreApplication extends Application {

    private CoreComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.component = DaggerCoreComponent
                .builder()
                .application(this)
                .build();

        this.component.inject(this);
    }

    public CoreComponent getComponent() {
        return this.component;
    }

    public void setComponent(CoreComponent component) {
        this.component = component;
    }

}
