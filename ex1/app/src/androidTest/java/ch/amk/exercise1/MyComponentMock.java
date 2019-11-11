package ch.amk.exercise1;

import ch.amk.exercise1.modules.Main;
import dagger.Component;

@Component(modules = Main.class)
public interface MyComponentMock extends MyComponent {
}
