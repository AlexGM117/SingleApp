package com.softhink.single;

import com.softhink.single.models.response.BaseResponse;

public interface BaseView {
    void serviceUnavailable();
    void showProgress();
    void hideProgress();

    interface Interactor<T, U> {
        void onResponseSuccess(BaseResponse<T, U> response);
        void onResponseError(U error);
        void onFailed();
    }
}
