package com.example.multithreadingtestapp.screen.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.multithreadingtestapp.screen.main.MainPresenter.*;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.ResultType;

public class MainRepository implements MainContract.Model {

    private final int listSize = 5000000;
    private final int listMidIndex = 2500000;
    private final int numberToAdd = 1;
    private final List<Integer> arrayListAdd = new ArrayList<>(Collections.nCopies(listSize, 0));
    private final List<Integer> linkedListAdd = new LinkedList<>(Collections.nCopies(listSize, 0));
    private final CopyOnWriteArrayList<Integer> copyOnWriteArrayListAdd = new CopyOnWriteArrayList<>(Collections.nCopies(listSize, 0));
    private final List<Integer> arrayListRemove = new ArrayList<>(Collections.nCopies(listSize, 0));
    private final List<Integer> linkedListRemove = new LinkedList<>(Collections.nCopies(listSize, 0));
    private final CopyOnWriteArrayList<Integer> copyOnWriteArrayListRemove = new CopyOnWriteArrayList<>(Collections.nCopies(listSize, 0));
    private final List<Integer> arrayListSearch = new ArrayList<>(Collections.nCopies(listSize, 0));
    private final List<Integer> linkedListSearch = new LinkedList<>(Collections.nCopies(listSize, 0));
    private final CopyOnWriteArrayList<Integer> copyOnWriteArrayListSearch = new CopyOnWriteArrayList<>(Collections.nCopies(listSize, 0));
    private final MainContract.Presenter presenter;
    private ExecutorService executor;

    // Adding element to search later
    {
        arrayListSearch.add(listMidIndex, numberToAdd);
        linkedListSearch.add(listMidIndex, numberToAdd);
        copyOnWriteArrayListSearch.add(listMidIndex, numberToAdd);
    }

    public MainRepository(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void startComputing() {
        executor = Executors.newFixedThreadPool(9);

        addMid(ADD_MID_ARRAYLIST, arrayListAdd);
        addMid(ADD_MID_LINKEDLIST, linkedListAdd);
        addMid(ADD_MID_COPYONWRITEARRAYLIST, copyOnWriteArrayListAdd);
        search(SEARCH_ARRAYLIST, arrayListSearch);
        search(SEARCH_LINKEDLIST, linkedListSearch);
        search(SEARCH_COPYONWRITEARRAYLIST, copyOnWriteArrayListSearch);
        removeMid(REMOVE_MID_ARRAYLIST, arrayListRemove);
        removeMid(REMOVE_MID_LINKEDLIST, linkedListRemove);
        removeMid(REMOVE_MID_COPYONWRITEARRAYLIST, copyOnWriteArrayListRemove);

        executor.shutdown();
    }

    private void addMid(@ResultType int resultType, List<Integer> list) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long timeStart = new Date().getTime();
                list.add(listMidIndex, numberToAdd);
                presenter.onComputingResult(resultType, new Date().getTime() - timeStart);
            }
        });
    }

    private void removeMid(@ResultType int resultType, List<Integer> list) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long timeStart = new Date().getTime();
                list.remove(listMidIndex);
                presenter.onComputingResult(resultType, new Date().getTime() - timeStart);
            }
        });
    }

    private void search(@ResultType int resultType, List<Integer> list) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long timeStart = new Date().getTime();
                if (list.contains(numberToAdd)) {
                    presenter.onComputingResult(resultType, new Date().getTime() - timeStart);
                } else {
                    presenter.onComputingResult(resultType, -1);
                }
            }
        });
    }
}
