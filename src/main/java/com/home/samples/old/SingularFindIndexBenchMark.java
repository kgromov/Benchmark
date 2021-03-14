package com.home.samples.old;

import com.home.samples.data.interview.find_index_in_array.ArrayFindIndex;
import com.home.samples.data.interview.find_index_in_array.FindIndex;
import com.home.samples.data.interview.find_index_in_array.MapFindIndex;
import com.home.samples.data.interview.find_index_in_array.SortedArrayFindIndex;
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
public class SingularFindIndexBenchMark {

    @Param({"10", "1000", "10000"})
    private int length;

    private int value;
    private int[] array;

    @Setup
    public void setup() {
        Random random = new Random();
        array = random.ints(length, 1, length).toArray();
        value = random.nextInt(length / 2 + 1);
    }

    @Benchmark
    public void getIndexOfMap(Blackhole bh)
    {
        FindIndex findIndex = new MapFindIndex(array);
        bh.consume(findIndex.indexOf(value));
    }

    @Benchmark
    public void getIndexOfArray(Blackhole bh)
    {
        FindIndex findIndex = new ArrayFindIndex(array);
        bh.consume(findIndex.indexOf(value));
    }

    @Benchmark
    public void getIndexSortedArray(Blackhole bh)
    {
        FindIndex findIndex = new SortedArrayFindIndex(array);
        bh.consume(findIndex.indexOf(value));
    }

}
