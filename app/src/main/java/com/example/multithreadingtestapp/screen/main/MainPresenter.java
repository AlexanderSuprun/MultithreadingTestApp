package com.example.multithreadingtestapp.screen.main;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.ExecutorService;

public class MainPresenter implements MainContract.Presenter {

    public static final int ADD_MID_ARRAYLIST = 0;
    public static final int ADD_MID_LINKEDLIST = 1;
    public static final int ADD_MID_COPYONWRITEARRAYLIST = 2;
    public static final int REMOVE_MID_ARRAYLIST = 3;
    public static final int REMOVE_MID_LINKEDLIST = 4;
    public static final int REMOVE_MID_COPYONWRITEARRAYLIST = 5;
    public static final int SEARCH_ARRAYLIST = 6;
    public static final int SEARCH_LINKEDLIST = 7;
    public static final int SEARCH_COPYONWRITEARRAYLIST = 8;

    private MainContract.View view;
    private MainContract.Model repository;

    public MainPresenter(MainContract.View view, ExecutorService executor) {
        this.view = view;
        this.repository = new MainRepository(this, executor);
    }

    @Override
    public void onStartButtonPressed() {
        repository.startComputing();
    }

    @Override
    public void computingResult(@ResultType int result, double value) {
        view.setResult(result, String.valueOf(value));
    }

    @Override
    public void dropView() {
        view = null;
        repository.dropPresenter();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ADD_MID_ARRAYLIST, ADD_MID_LINKEDLIST, ADD_MID_COPYONWRITEARRAYLIST,
            REMOVE_MID_ARRAYLIST, REMOVE_MID_LINKEDLIST, REMOVE_MID_COPYONWRITEARRAYLIST,
            SEARCH_ARRAYLIST, SEARCH_LINKEDLIST, SEARCH_COPYONWRITEARRAYLIST})
    public @interface ResultType {
    }
}
