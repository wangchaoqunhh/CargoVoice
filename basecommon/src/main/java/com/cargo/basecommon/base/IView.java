package com.cargo.basecommon.base;


public interface IView<R> {
    void onSuccess(R r);
    void onError(String result);
}
