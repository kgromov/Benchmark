package com.home.samples;

import com.home.samples.service.RandomGenerationBenchmark;
import com.home.samples.strings.StringConcatenationBenchmark;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class ExtendedBenchmarkRunner {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringConcatenationBenchmark.class.getSimpleName())
//                .include(RandomGenerationBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(10))
                .forks(2) //0 makes debugging possible
                .shouldFailOnError(true)
//                .resultFormat(ResultFormatType.JSON)
//                .result("output/benchmark.json")
				.shouldDoGC(false)
                .jvmArgsAppend(
//						"-Xint",
//						"-XX:+UnlockDiagnosticVMOptions",
//						"-XX:TieredStopAtLevel=1"
//						"-XX:+PrintCompilation",
//						"-XX:+PrintInlining",
//						"-XX:+LogCompilation"
                )
//                .addProfiler(GCProfiler.class)// memory and GC profiler
//                .addProfiler(HotspotMemoryProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
