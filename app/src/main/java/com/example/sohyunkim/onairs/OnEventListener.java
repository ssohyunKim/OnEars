package com.example.sohyunkim.onairs;

public interface OnEventListener<T> {
    public void onSuccess(T object);
    public void onFailure(Exception e);
}