package com.example.multithreadingtestapp.screen.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class MainRepository implements MainContract.Model {

    private MainContract.Presenter presenter;
    private ExecutorService executor;
    private int listSize = 1000000;

    public MainRepository(MainContract.Presenter presenter, ExecutorService executor) {
        this.presenter = presenter;
        this.executor = executor;
    }

    @Override
    public void startComputing() {
        ArrayList<Integer> arrayList = new ArrayList<>(listSize);
        LinkedList<Integer> linkedList = new LinkedList<>();
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < listSize; i++) {
//                    arrayList.add(ThreadLocalRandom.current().nextInt(0, 1001));
//                    linkedList.add(ThreadLocalRandom.current().nextInt(0, 1001));
//                    copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(0, 1001));
//                }
//                Log.d("TAG_THREAD", Thread.currentThread().getName());
//                Log.d("TAG_ALIST_SIZE", "" + arrayList.size());
//                Log.d("TAG_LLIST_SIZE", "" + linkedList.size());
//                Log.d("TAG_COWALIST_SIZE", "" + copyOnWriteArrayList.size());
//            }
//        });

//        presenter.computingResult();
        executor.shutdown();
    }

    @Override
    public void dropPresenter() {
        presenter = null;
    }
}
