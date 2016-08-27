package com.github.denisura.coursera.ui;

import android.support.v4.app.Fragment;

import com.github.denisura.coursera.CourseraApplication;
import com.squareup.leakcanary.RefWatcher;

public class BaseFragment
        extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = CourseraApplication.getRefWatcher();
        refWatcher.watch(this);
    }
}