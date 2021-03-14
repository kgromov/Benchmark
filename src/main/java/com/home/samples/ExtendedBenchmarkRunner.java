package com.home.samples;

import com.home.samples.old.EnumBenchMark;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class ExtendedBenchmarkRunner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(EnumBenchMark.class.getSimpleName())
                .warmupIterations(10)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(10)
                .measurementTime(TimeValue.seconds(10))
                .forks(5) //0 makes debugging possible
                .shouldFailOnError(true)
//				.shouldDoGC(false)
                .jvmArgsAppend(
//						"-Xint",
//						"-XX:+UnlockDiagnosticVMOptions",
//						"-XX:TieredStopAtLevel=1"
//						"-XX:+PrintCompilation",
//						"-XX:+PrintInlining",
//						"-XX:+LogCompilation"
                )
                .addProfiler(GCProfiler.class)// memory and GC profiler
                .build();

        new Runner(opt).run();
    }
}
