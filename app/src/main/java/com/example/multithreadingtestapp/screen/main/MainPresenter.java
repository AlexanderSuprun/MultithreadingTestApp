package com.example.multithreadingtestapp.screen.main;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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

    private final MainContract.Model repository;
    private MainContract.View view;
    private byte counter = 0;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.repository = new MainRepository(this);
    }

    @Override
    public void onStartButtonPressed() {
        view.clearValues();
        view.showProgress();
        repository.startComputing();
    }

    @Override
    public void onComputingResult(@ResultType int resultType, long value) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                view.setResult(resultType, value);
                counter++;
                if (counter == 9) {
                    view.hideProgress();
                    counter = 0;
                }
            }
        });
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ADD_MID_ARRAYLIST, ADD_MID_LINKEDLIST, ADD_MID_COPYONWRITEARRAYLIST,
            REMOVE_MID_ARRAYLIST, REMOVE_MID_LINKEDLIST, REMOVE_MID_COPYONWRITEARRAYLIST,
            SEARCH_ARRAYLIST, SEARCH_LINKEDLIST, SEARCH_COPYONWRITEARRAYLIST})
    public @interface ResultType {}
}
