package com.softhink.single;

import com.softhink.single.models.response.BaseResponse;

public interface BaseView {
    void serviceUnavailable();
    void showProgress();
    void hideProgress();

    interface Interactor<T> {
        void onResponseSuccess(BaseResponse<T> response);
        void onResponseError();
        void onFailed();
    }
}
