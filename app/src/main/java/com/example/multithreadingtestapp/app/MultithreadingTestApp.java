package com.example.multithreadingtestapp.app;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingTestApp extends Application {

    private ExecutorService executor;

    @Override
    public void onCreate() {
        super.onCreate();

        executor = Executors.newFixedThreadPool(9);
    }

    public ExecutorService getAppExecutor() {
        return executor;
    }
}
