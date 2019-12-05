package ch.amk.exercise3.api;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;
import ch.amk.exercise3.api.di.MockApplication;

public class TestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return super.newApplication(cl, MockApplication.class.getName(), context);
    }
}
