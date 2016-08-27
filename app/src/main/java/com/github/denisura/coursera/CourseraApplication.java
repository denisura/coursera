package com.github.denisura.coursera;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;


public class CourseraApplication extends Application {

    private static CourseraApplication _instance;
    private RefWatcher _refWatcher;

    public static CourseraApplication get() {
        return _instance;
    }

    public static RefWatcher getRefWatcher() {
        return CourseraApplication.get()._refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = (CourseraApplication) getApplicationContext();
        _refWatcher = LeakCanary.install(this);

        Timber.plant(new Timber.DebugTree());
    }
}
