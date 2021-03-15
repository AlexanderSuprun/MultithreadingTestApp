package com.example.multithreadingtestapp.screen.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.multithreadingtestapp.screen.main.MainPresenter.*;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.ResultType;

public class MainRepository implements MainContract.Model {

    private final int listSize = 5000000;
    private final int listMidIndex = 2500000;
    private final int numberToAdd = 1;
    private final MainContract.Presenter presenter;
    private List<Integer> arrayListAdd;
    private List<Integer> arrayListRemove;
    private List<Integer> arrayListSearch;
    private List<Integer> linkedListAdd;
    private List<Integer> linkedListRemove;
    private List<Integer> linkedListSearch;
    private CopyOnWriteArrayList<Integer> copyOnWriteArrayListAdd;
    private CopyOnWriteArrayList<Integer> copyOnWriteArrayListRemove;
    private CopyOnWriteArrayList<Integer> copyOnWriteArrayListSearch;
    private ExecutorService executor;
    private CountDownLatch latch;

    public MainRepository(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void startComputing() {
        executor = Executors.newFixedThreadPool(9);
        latch = new CountDownLatch(1);

        // Initiates lists and adds an item to search later
        executor.execute(new Runnable() {
            @Override
            public void run() {
                arrayListAdd = new ArrayList<>(Collections.nCopies(listSize, 0));
                linkedListAdd = new LinkedList<>(Collections.nCopies(listSize, 0));
                copyOnWriteArrayListAdd = new CopyOnWriteArrayList<>(Collections.nCopies(listSize, 0));
                arrayListRemove = new ArrayList<>(Collections.nCopies(listSize, 0));
                linkedListRemove = new LinkedList<>(Collections.nCopies(listSize, 0));
                copyOnWriteArrayListRemove = new CopyOnWriteArrayList<>(Collections.nCopies(listSize, 0));
                arrayListSearch = new ArrayList<>(Collections.nCopies(listSize, 0));
                linkedListSearch = new LinkedList<>(Collections.nCopies(listSize, 0));
                copyOnWriteArrayListSearch = new CopyOnWriteArrayList<>(Collections.nCopies(listSize, 0));
                arrayListSearch.add(listMidIndex, numberToAdd);
                linkedListSearch.add(listMidIndex, numberToAdd);
                copyOnWriteArrayListSearch.add(listMidIndex, numberToAdd);
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addMid(ADD_MID_ARRAYLIST, arrayListAdd);
        addMid(ADD_MID_LINKEDLIST, linkedListAdd);
        addMid(ADD_MID_COPYONWRITEARRAYLIST, copyOnWriteArrayListAdd);
        removeMid(REMOVE_MID_ARRAYLIST, arrayListRemove);
        removeMid(REMOVE_MID_LINKEDLIST, linkedListRemove);
        removeMid(REMOVE_MID_COPYONWRITEARRAYLIST, copyOnWriteArrayListRemove);
        search(SEARCH_ARRAYLIST, arrayListSearch);
        search(SEARCH_LINKEDLIST, linkedListSearch);
        search(SEARCH_COPYONWRITEARRAYLIST, copyOnWriteArrayListSearch);

        executor.shutdown();
    }

    private void addMid(@ResultType int resultType, List<Integer> list) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long timeStart = System.currentTimeMillis();
                list.add(listMidIndex, numberToAdd);
                presenter.onComputingResult(resultType, System.currentTimeMillis() - timeStart);
            }
        });
    }

    private void removeMid(@ResultType int resultType, List<Integer> list) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long timeStart = System.currentTimeMillis();
                list.remove(listMidIndex);
                presenter.onComputingResult(resultType, System.currentTimeMillis() - timeStart);
            }
        });
    }

    private void search(@ResultType int resultType, List<Integer> list) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long timeStart = System.currentTimeMillis();
                if (list.contains(numberToAdd)) {
                    presenter.onComputingResult(resultType, System.currentTimeMillis() - timeStart);
                } else {
                    presenter.onComputingResult(resultType, -1);
                }
            }
        });
    }

    @Override
    public void clearLists() {
        arrayListAdd = null;
        arrayListRemove = null;
        arrayListSearch = null;
        linkedListAdd = null;
        linkedListRemove = null;
        linkedListSearch = null;
        copyOnWriteArrayListAdd = null;
        copyOnWriteArrayListRemove = null;
        copyOnWriteArrayListSearch = null;
    }
}
