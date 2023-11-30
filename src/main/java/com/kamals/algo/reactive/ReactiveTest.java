package com.kamals.algo.reactive;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

public class ReactiveTest
{
    public static void main(String[] args)
    {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10)
                    {
                        sink.complete();
                    }
                    return state;
                }, state -> System.out.println("state: " + state));
        flux.subscribe(System.out::println);
    }
}
