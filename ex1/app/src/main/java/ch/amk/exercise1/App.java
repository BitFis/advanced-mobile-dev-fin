package ch.amk.exercise1;

import android.app.Application;

import dagger.Component;

public class App extends Application {

    private MyComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        this.component = DaggerMyComponent.builder()
                .application(this)
                .build();

        this.component.inject(this);
    }

    public MyComponent getComponent() {
        return component;
    }

    public void setComponent(MyComponent component) {
        this.component = component;
    }
}
