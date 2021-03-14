package com.home.samples.old;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by konstantin on 31.03.2020.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class IntBoxingBenchMark {

    @Param({"100", "1000", "10000"})
    private int range;

    private int number;

    @Setup
    public void setup()
    {
        number = new Random().nextInt(range);
        System.out.println(String.format("Number = %d, range = [0; %d]", number, range));
    }

    @Benchmark
    public void implicitBoxing(Blackhole bh)
    {
        Integer boxed = number;
        bh.consume(boxed);
    }

    @Benchmark
    public void valueOf(Blackhole bh)
    {
        Integer boxed = Integer.valueOf(number);
        bh.consume(boxed);
    }

    @Benchmark
    public void explicitBoxingViaNew(Blackhole bh)
    {
        Integer boxed = new Integer(number);
        bh.consume(boxed);
    }

    @Benchmark
    public void explicitBoxingViaCast(Blackhole bh)
    {
        Integer boxed = Integer.class.cast(number);
        bh.consume(boxed);
    }
}
