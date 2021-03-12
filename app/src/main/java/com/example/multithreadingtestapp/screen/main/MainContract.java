package com.example.multithreadingtestapp.screen.main;

public interface MainContract {

    interface View {

        void setResult(@MainPresenter.ResultType int resultType, String value);
    }

    interface Presenter {

        void dropView();

        void onStartButtonPressed();

        void computingResult(@MainPresenter.ResultType int result, double value);
    }

    interface Model {

        void startComputing();

        void dropPresenter();
    }
}
