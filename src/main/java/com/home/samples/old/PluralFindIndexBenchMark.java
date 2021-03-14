package com.home.samples.old;

import com.home.samples.data.interview.find_index_in_array.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
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
public class PluralFindIndexBenchMark {

    @Param({"10", "100", "1000"})
    private int length;

    private int value;
    private int times;
    private FindIndex mapFindIndex;
    private FindIndex arrayFindIndex;
    private FindIndex sortedArrayFindIndex;
    private FindIndex sortedWithThresholdArrayFindIndex;

    @Setup()
    public void setup() {
        Random random = new Random();
        int[] array = random.ints(length, 1, length).toArray();
        value = random.nextInt(length / 2 + 1);
        times = random.nextInt(length / 2 + 1);
        mapFindIndex = new MapFindIndex(array);
        arrayFindIndex = new ArrayFindIndex(array);
        sortedArrayFindIndex = new SortedArrayFindIndex(Arrays.copyOf(array, array.length));
        sortedWithThresholdArrayFindIndex = new SortedWithThresholdArrayFindIndex(Arrays.copyOf(array, array.length));
        System.out.println(String.format("value = %d, times = %d, array = %s", value, times, Arrays.toString(array)));
    }

    @Benchmark
    public void getIndexOfMap(Blackhole bh)
    {
        for (int i = 0; i < times; i++)
        {
            bh.consume(mapFindIndex.indexOf(value));
        }
    }

    @Benchmark
    public void getIndexOfArray(Blackhole bh)
    {
        for (int i = 0; i < times; i++)
        {
            bh.consume(arrayFindIndex.indexOf(value));
        }
    }

    @Benchmark
    public void getIndexSortedArray(Blackhole bh)
    {
        for (int i = 0; i < times; i++)
        {
            bh.consume(sortedArrayFindIndex.indexOf(value));
        }
    }

    @Benchmark
    public void getIndexSortedWithThresholdArray(Blackhole bh)
    {
        for (int i = 0; i < times; i++)
        {
            bh.consume(sortedWithThresholdArrayFindIndex.indexOf(value));
        }
    }
}
