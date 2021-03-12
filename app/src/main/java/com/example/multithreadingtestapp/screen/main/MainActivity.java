package com.example.multithreadingtestapp.screen.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.multithreadingtestapp.R;
import com.example.multithreadingtestapp.app.MultithreadingTestApp;

import static com.example.multithreadingtestapp.screen.main.MainPresenter.*;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        presenter = new MainPresenter(this, ((MultithreadingTestApp) getApplication()).getAppExecutor());
    }

    @Override
    public void setResult(@MainPresenter.ResultType int resultType, String value) {
        switch (resultType) {
            case ADD_MID_ARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_addmid_arraylist)).setText(value);
                break;
            case ADD_MID_LINKEDLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_addmid_linkedlist)).setText(value);
                break;
            case ADD_MID_COPYONWRITEARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_addmid_copyonwritearraylist)).setText(value);
                break;
            case REMOVE_MID_ARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_removemid_arraylist)).setText(value);
                break;
            case REMOVE_MID_LINKEDLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_removemid_linkedlist)).setText(value);
                break;
            case REMOVE_MID_COPYONWRITEARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_removemid_copyonwritearraylist)).setText(value);
                break;
            case SEARCH_ARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_search_arraylist)).setText(value);
                break;
            case SEARCH_LINKEDLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_search_linkedlist)).setText(value);
                break;
            case SEARCH_COPYONWRITEARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_search_copyonwritearraylist)).setText(value);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_start) {
            presenter.onStartButtonPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}