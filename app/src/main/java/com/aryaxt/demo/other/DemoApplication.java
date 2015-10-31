package com.aryaxt.demo.other;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by aryaxt on 10/31/15.
 */
public class DemoApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new ApplicationModule(this));
    }

    public ObjectGraph getApplicationGraph() {
        return objectGraph;
    }
}
