package com.aisino.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: xiajun003
 * @Date: 2018/12/29 18:08
 * @Description:
 */
public class HelloWord {
    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(1, 2, 3).map(i -> i * 2).flatMap(j -> Stream.of(j*3));
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.err.println(collect);
        /**
         * Reactor  中的发布者由 Flux 和 Mono 两个类定义，它们都提供了丰富的 操作符
         *
         * Flux和Mono可以发出三种数据信号:
         *    元数值
         *    错误信号
         *    完成信号
         *
         * Flux和Mono提供了多种创建数据流的方法
         *  1.Flux.just();//传入的参数就是数据流的元素
         *  2.Flux.fromArray();//根据数组传入数据流
         *  3.Flux.fromIterable();//根据集合传入数据流
         *  4.Flux.fromStream();//根据 java Stream 传入数据流
         *
         *
         * 订阅
         *      订阅前什么都不会发生
         *
         * subscribe(); 订阅并触发数据流
         *
         * subscribe(Consumer<? super T> consumer); 订阅并指定对正常数据元素如何处理
         *
         *  // 订阅并定义对正常数据元素和错误信号的处理
         * subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> errorConsumer);
         *
         * // 订阅并定义对正常数据元素、错误信号和完成信号的处理
         * subscribe(Consumer<? super T> consumer,Consumer<? super Throwable> errorConsumer,Runnable completeConsumer);
         *
         * // 订阅并定义对正常数据元素、错误信号和完成信号的处理，以及订阅发生时的处理逻辑
         * subscribe(Consumer<? super T> consumer,Consumer<? super Throwable> errorConsumer,Runnable completeConsumer,Consumer<? super Subscription> subscriptionConsumer);
         *
         */

        Mono<List<Integer>> listMono = Flux.just(1, 2, 3, 4, 5).collectList();

    }
}
