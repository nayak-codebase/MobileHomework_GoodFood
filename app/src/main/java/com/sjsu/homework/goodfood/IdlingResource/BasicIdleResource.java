package com.sjsu.homework.goodfood.IdlingResource;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;



public class BasicIdleResource implements IdlingResource {

    @Nullable private volatile ResourceCallback checkCallback;

    private AtomicBoolean checkIfIdle = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return checkIfIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        checkCallback = callback;
    }
    public void setIdleState(boolean isIdleNow) {
        checkIfIdle.set(isIdleNow);
        if (isIdleNow && checkCallback != null) {
            checkCallback.onTransitionToIdle();
        }
    }
}