package com.home.samples;

import com.home.samples.collections.AddElementToTheEndBenchmark;
import com.home.samples.collections.MapIterateBenchMark;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by konstantin on 04.10.2020.
 */
public class BenchmarkRunner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
//                .include(AddElementToTheEndBenchmark.class.getSimpleName())
                .include(MapIterateBenchMark.class.getSimpleName())
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
