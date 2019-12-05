package ch.amk.exercise3.api.di;

import ch.amk.exercise3.api.CoreApplication;
import dagger.Component;

public class MockApplication extends CoreApplication {
    private MockComponent component;

    @Override
    protected void createComponent() {
        this.component = DaggerMockComponent.create();
        this.component.inject(this);
    }

    public MockComponent getComponent() {
        return this.component;
    }
}
