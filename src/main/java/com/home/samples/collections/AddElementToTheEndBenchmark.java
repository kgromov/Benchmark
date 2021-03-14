package com.home.samples.collections;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 04.10.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class AddElementToTheEndBenchmark {
    private static final int [] SIZES = new int [] {10, 100};
    private final static InitCollectionData collectionData = InitCollectionData.getInstance(SIZES);

    @Param({"10","100"})
    private int N;

    private List<String> arrayList;
    private List<String> linkedList;
    private Set<String> hashSet;
    private Set<String> treeSet;
    private Set<String> linkedHashSet;

    @Setup
    public void setup() {
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        treeSet = new TreeSet<>();
        linkedHashSet = new LinkedHashSet<>();
    }

    @Benchmark
    public void addToArrayList(Blackhole blackhole) {
        List<String> data = collectionData.getData().get(N);
        for (String value : data) {
            blackhole.consume(arrayList.add(value));
        }
    }

    @Benchmark
    public void addToArrayLinkedList(Blackhole blackhole) {
        List<String> data = collectionData.getData().get(N);
        for (String value : data) {
            blackhole.consume(linkedList.add(value));
        }
    }

    @Benchmark
    public void addToArrayHashSet(Blackhole blackhole) {
        List<String> data = collectionData.getData().get(N);
        for (String value : data) {
            blackhole.consume(hashSet.add(value));
        }
    }

    @Benchmark
    public void addToArrayTreeSet(Blackhole blackhole) {
        List<String> data = collectionData.getData().get(N);
        for (String value : data) {
            blackhole.consume(treeSet.add(value));
        }
    }

    @Benchmark
    public void addToArrayLinkedHashSet(Blackhole blackhole) {
        List<String> data = collectionData.getData().get(N);
        for (String value : data) {
            blackhole.consume(linkedHashSet.add(value));
        }
    }
}
