package com.an.paginglibrary.sample.viewmodel;

import android.content.Context;

import java.util.Observable;

import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractViewModel extends Observable {

    protected Context context;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public abstract void initializeViews();
    public abstract void fetchData();

    public abstract void onSuccessResponse(Object object);
    public abstract void onFailureResponse(Throwable t);

    protected void refreshView(Object obj) {
        setChanged();
        notifyObservers(obj);
    }

    protected void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}