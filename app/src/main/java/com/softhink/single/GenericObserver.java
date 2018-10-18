package com.softhink.single;

import com.softhink.single.registro.Status;

import androidx.lifecycle.Observer;

public class GenericObserver<T> implements Observer<T> {
    private Status status;
    private T t;

    public GenericObserver(Status status, T t) {
        this.status = status;
        this.t = t;
    }

    @Override
    public void onChanged(T t) {
    }

    public Status getStatus(){
        return status;
    }

    public T getData(){
        return t;
    }
}
