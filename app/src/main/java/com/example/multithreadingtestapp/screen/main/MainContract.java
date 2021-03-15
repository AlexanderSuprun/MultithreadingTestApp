package com.example.multithreadingtestapp.screen.main;

public interface MainContract {

    interface View {

        void setResult(@MainPresenter.ResultType int resultType, long value);

        void showProgress();

        void hideProgress();

        void clearValues();
    }

    interface Presenter {

        void dropView();

        void onStartButtonPressed();

        void onComputingResult(@MainPresenter.ResultType int resultType, long value);

        void onListsInitComplete();
    }

    interface Model {

        void initLists();

        void startComputing();

    }
}
