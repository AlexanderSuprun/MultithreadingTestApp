package com.example.multithreadingtestapp.screen.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.example.multithreadingtestapp.R;

import static com.example.multithreadingtestapp.screen.main.MainPresenter.ADD_MID_ARRAYLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.ADD_MID_COPYONWRITEARRAYLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.ADD_MID_LINKEDLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.REMOVE_MID_ARRAYLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.REMOVE_MID_COPYONWRITEARRAYLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.REMOVE_MID_LINKEDLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.ResultType;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.SEARCH_ARRAYLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.SEARCH_COPYONWRITEARRAYLIST;
import static com.example.multithreadingtestapp.screen.main.MainPresenter.SEARCH_LINKEDLIST;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private ProgressBar loaderBlock;
    private Menu actionBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        loaderBlock = findViewById(R.id.progress_circular_activity_main);
        presenter = new MainPresenter(this);
    }

    @Override
    public void setResult(@ResultType int resultType, long value) {
        switch (resultType) {
            case ADD_MID_ARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_addmid_arraylist))
                        .setText(getString(R.string.result_value, value));
                break;
            case ADD_MID_LINKEDLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_addmid_linkedlist))
                        .setText(getString(R.string.result_value, value));
                break;
            case ADD_MID_COPYONWRITEARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_addmid_copyonwritearraylist))
                        .setText(getString(R.string.result_value, value));
                break;
            case REMOVE_MID_ARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_removemid_arraylist))
                        .setText(getString(R.string.result_value, value));
                break;
            case REMOVE_MID_LINKEDLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_removemid_linkedlist))
                        .setText(getString(R.string.result_value, value));
                break;
            case REMOVE_MID_COPYONWRITEARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_removemid_copyonwritearraylist))
                        .setText(getString(R.string.result_value, value));
                break;
            case SEARCH_ARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_search_arraylist))
                        .setText(getString(R.string.result_value, value));
                break;
            case SEARCH_LINKEDLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_search_linkedlist))
                        .setText(getString(R.string.result_value, value));
                break;
            case SEARCH_COPYONWRITEARRAYLIST:
                ((AppCompatTextView) findViewById(R.id.text_view_search_copyonwritearraylist))
                        .setText(getString(R.string.result_value, value));
        }
    }

    @Override
    public void clearValues() {
        ((AppCompatTextView) findViewById(R.id.text_view_addmid_arraylist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_addmid_linkedlist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_addmid_copyonwritearraylist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_removemid_arraylist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_removemid_linkedlist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_removemid_copyonwritearraylist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_search_arraylist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_search_linkedlist))
                .setText("");
        ((AppCompatTextView) findViewById(R.id.text_view_search_copyonwritearraylist))
                .setText("");
    }

    @Override
    public void showProgress() {
        loaderBlock.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loaderBlock.setVisibility(View.INVISIBLE);
        if (actionBarMenu != null) {
            actionBarMenu.getItem(0).setVisible(true);
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
        actionBarMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_start) {
            presenter.onStartButtonPressed();
            item.setVisible(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}