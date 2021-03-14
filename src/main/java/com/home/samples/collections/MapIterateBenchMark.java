package com.home.samples.collections;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
// no warmup = 1 fork per 20 iterations for Warmup and Measurement
public class MapIterateBenchMark {

    @Param({"1000000"})
    private int N;

    private Map<String, Integer> DATA_FOR_TESTING;

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData();
    }

    @Benchmark
    public void loopEntrySet(Blackhole bh) {
        for (Map.Entry<String, Integer> entry : DATA_FOR_TESTING.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            bh.consume(key);
            bh.consume(value);
        }
    }

    @Benchmark
    public void loopKeySet(Blackhole bh) {
        for (String key : DATA_FOR_TESTING.keySet()) {
            Integer value = DATA_FOR_TESTING.get(key);
            bh.consume(value);
        }
    }

    @Benchmark
    public void loopValues(Blackhole bh) {
        for (Integer value : DATA_FOR_TESTING.values()) {
            bh.consume(value);
        }
    }

    @Benchmark
    public void loopEntryViaIterator(Blackhole bh) {
        Iterator<Map.Entry<String, Integer>> it = DATA_FOR_TESTING.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer>entry =  it.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            bh.consume(key);
            bh.consume(value);
        }
    }

    @Benchmark
    public void loopForEach(Blackhole bh) {
        DATA_FOR_TESTING.forEach((key, value) ->
        {
            bh.consume(key);
            bh.consume(value);
        });
    }

    private Map<String, Integer> createData() {
        Map<String, Integer> data = new HashMap<>();
        for (int i = 0; i < N; i++) {
            data.put("Number : " + i, i);
        }
        return data;
    }
}
