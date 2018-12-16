package com.aisino.RxJava;

import rx.Observable;
import rx.Subscriber;

public class TestRxJava {

    public static void main(String[] args) {
        //just 是一个工厂方法，用于构建一个Observable对象
        Observable<String> observable = Observable.just("One", "Two", "Three");
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable item) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String item) {
                System.out.println("Item is " + item);
            }
        });
    }

}


