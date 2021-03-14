package com.home.samples.old;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by konstantin on 31.03.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class IntArrayBoxingBenchMark {

    @Param({"100", "1000", "10000"})
    private int length;

    private int [] array;
    private Integer [] boxedArray;

    @Setup
    public void setup()
    {
        array = new Random().ints(length, 1, length).toArray();
        boxedArray = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            boxedArray[i] = array[i];
        }
    }

    @Benchmark
    public void boxArray(Blackhole bh)
    {
        for (Integer i : array)
        {
            bh.consume(i);
        }
    }

    @Benchmark
    public void unBoxArray(Blackhole bh)
    {
        for (int i : boxedArray)
        {
            bh.consume(i);
        }
    }

    @Benchmark
    public void intStream(Blackhole bh)
    {
        IntStream.range(0, array.length).boxed().forEach(bh::consume);
    }

    @Benchmark
    public void arrayStream(Blackhole bh)
    {
        Arrays.stream(array).forEach(bh::consume);
    }

    @Benchmark
    public void streamOf(Blackhole bh)
    {
        Stream.of(array).forEach(bh::consume);
    }
}
