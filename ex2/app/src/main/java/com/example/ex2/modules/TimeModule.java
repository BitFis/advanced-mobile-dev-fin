package com.example.ex2.modules;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TimeModule {

    private DateTimeFormatter provideWithPattern(String pattern) {
        return DateTimeFormatter.ofPattern(pattern)
                .withLocale(Locale.ENGLISH)
                .withZone(ZoneId.systemDefault());
    }

    @Provides @Named("date")
    public DateTimeFormatter provideDate() {
        return this.provideWithPattern("dd.MM.yyyy");
    }

    @Provides @Named("time")
    public DateTimeFormatter provideTime() {
        return this.provideWithPattern("HH:mm");
    }

    @Provides @Named("datetime")
    public DateTimeFormatter provideDateAndTime() {
        return this.provideWithPattern("dd.MM.yyyy HH:mm");
    }

}
