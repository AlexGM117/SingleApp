package com.softhink.single;

public interface BaseView {
    void serviceUnavailable();
    void showProgress();
    void hideProgress();

    interface Interactor<T> {
        void onResponseSuccess(T t);
        void onResponseError(T t);
        void onFailed();
    }
}
