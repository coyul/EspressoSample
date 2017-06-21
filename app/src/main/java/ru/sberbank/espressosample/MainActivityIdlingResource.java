package ru.sberbank.espressosample;

import android.support.test.espresso.*;
import android.util.Log;

public class MainActivityIdlingResource implements IdlingResource {

    private ResourceCallback mCallback;
    //переменная отвечает за то, идет загрузка (false) или завершилась (true)
    private boolean mIsIdleNow = true;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    public void setIdleState(Boolean state) {
        mIsIdleNow = state;
        //метод вызывается у колбэка, когда загрузка завершилась
        if (mIsIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }


}
