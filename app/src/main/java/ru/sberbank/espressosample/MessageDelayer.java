package ru.sberbank.espressosample;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

public class MessageDelayer {
    private static final int DELAY_MILLIS = 10000;

    public interface DelayerCallback {
        void onDone(String text);
    }

    public static void processMessage(final String message, final DelayerCallback callback,
                               @Nullable final MainActivityIdlingResource idlingResource) {
        if (idlingResource != null) idlingResource.setIdleState(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (callback != null) callback.onDone(message);
                if (idlingResource != null)
                {
                    idlingResource.setIdleState(true);
                }

            }
        }, DELAY_MILLIS);

    }
}
