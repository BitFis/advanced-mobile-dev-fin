package com.example.ex2;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class OpenStreetMapActivityTest {

    @Rule
    ActivityTestRule<OpenStreetMapActivity> activityTestRule = new ActivityTestRule<>(
            OpenStreetMapActivity.class,
            false,
            true
    )

    @Test
    public void testOpenStreetMapLocation() {

    }

}
