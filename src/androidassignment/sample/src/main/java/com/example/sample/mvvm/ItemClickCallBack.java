package com.example.sample.mvvm;

public interface ItemClickCallBack<T> {
    void onItemClick(int position, T data);
}
