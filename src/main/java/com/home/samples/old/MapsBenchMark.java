package com.home.samples.old;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
// no warmup = 1 fork per 20 iterations for Warmup and Measurement
public class MapsBenchMark {

    @Param({"1000", "1000000"})
    private int N;

    @Benchmark
    public void put(Blackhole bh) {
        Map<String, Integer> data = new HashMap<>();
        for (int i = 0; i < N; i++) {
            data.put("Number : " + i % (N /10), i);
            bh.consume(i);
        }
    }

    @Benchmark
    public void putIfAbsent(Blackhole bh) {
        Map<String, Integer> data = new HashMap<>();
        for (int i = 0; i < N; i++) {
            data.putIfAbsent("Number : " + i % (N /10), i);
            bh.consume(i);
        }
    }

    @Benchmark
    public void computeIfAbsent(Blackhole bh) {
        Map<String, AtomicInteger> data = new HashMap<>();
        for (int i = 0; i < N; i++) {
            data.computeIfAbsent("Number : " + i % (N /10), value -> new AtomicInteger()).incrementAndGet();
            bh.consume(i);
        }
    }

    @Benchmark
    public void merge(Blackhole bh) {
        Map<String, AtomicInteger> data = new HashMap<>();
        for (int i = 0; i < N; i++) {
            data.merge("Number : " + i % (N /10),  new AtomicInteger(), (a, b) -> new AtomicInteger(a.get() + b.get()));
            bh.consume(i);
        }
    }
    // compute; merge
}
