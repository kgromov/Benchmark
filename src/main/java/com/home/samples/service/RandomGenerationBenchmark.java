package com.home.samples.service;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class RandomGenerationBenchmark {

    @Benchmark
    public int nextInt_random() {
        return new Random().nextInt();
    }

    @Benchmark
    public int nextInt_randomSeed() {
        return new Random(1).nextInt();
    }

    @Benchmark
    public int nextInt_threadLocalRandom() {
        return ThreadLocalRandom.current().nextInt();
    }
}
