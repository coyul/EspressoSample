package ru.sberbank.espressosample.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.test.espresso.*;

import ru.sberbank.espressosample.MainActivityIdlingResource;
import ru.sberbank.espressosample.MessageDelayer;
import ru.sberbank.espressosample.R;

public class MainActivity extends AppCompatActivity implements MessageDelayer.DelayerCallback {

    private Button mButton;
    private Button mResultButton;
    private Button mLoadButton;
    private TextView mHelloWorldView;
    private TextView mHiddenView;
    private TextView mLoadingView;

    private static final String LOADING_TEXT = "Finally loaded";

    @Nullable
    public MainActivityIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setUpOnButtonsClickListeners();
    }

    private void setUpViews() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mButton = (Button) findViewById(R.id.button);
        mResultButton = (Button) findViewById(R.id.result_button);
        mLoadButton = (Button) findViewById(R.id.loading_button);
        mHelloWorldView = (TextView) findViewById(R.id.center_text);
        mHiddenView = (TextView) findViewById(R.id.invisible_text);
        mLoadingView = (TextView) findViewById(R.id.loading_text);
    }

    private void setUpOnButtonsClickListeners() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHelloWorldView.setText(R.string.center_text);

            }
        });

        mButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mHiddenView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageDelayer.processMessage(LOADING_TEXT, MainActivity.this, mIdlingResource);
            }
        });
    }


    @Override
    public void onDone(String text) {
        mLoadingView.setText(text);
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new MainActivityIdlingResource();
        }
        return mIdlingResource;
    }
}
