package com.home.samples.old;

import com.home.samples.data.interview.find_pair_index.ArrayPairDiffFinder;
import com.home.samples.data.interview.find_pair_index.MapPairDiffFinder;
import com.home.samples.data.interview.find_pair_index.PairDiffFinder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 21.03.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class PairIndexBenchMark {

    @Param({"10", "1000", "1000000"})
    private int length;

    private int k;
    private int[] array;

    @Setup
    public void setup() {
        Random random = new Random();
        array = random.ints(length, 1, 10).toArray();
        k = random.nextInt(length / 2 + 1);
    }

    @Benchmark
    public void getMaxPairIndexMap(Blackhole bh)
    {
        PairDiffFinder pairDiffFinder = new MapPairDiffFinder();
        bh.consume(pairDiffFinder.getMaxPairIndex(array));
    }

    @Benchmark
    public void getMaxPairIndexMapBuIndex(Blackhole bh)
    {
        PairDiffFinder pairDiffFinder = new MapPairDiffFinder();
        bh.consume(pairDiffFinder.getMaxPairIndex(array, k));
    }

    @Benchmark
    public void getMaxPairIndexArray(Blackhole bh)
    {
        PairDiffFinder pairDiffFinder = new ArrayPairDiffFinder();
        bh.consume(pairDiffFinder.getMaxPairIndex(array));
    }

    @Benchmark
    public void getMaxPairIndexArrayBuIndex(Blackhole bh)
    {
        PairDiffFinder pairDiffFinder = new ArrayPairDiffFinder();
        bh.consume(pairDiffFinder.getMaxPairIndex(array, k));
    }
}
