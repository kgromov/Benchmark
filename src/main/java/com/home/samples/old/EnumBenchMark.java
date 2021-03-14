package com.home.samples.old;

import com.home.samples.data.FlexibleAttributeError;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 10)
public class EnumBenchMark {

    private Map<String, Enum> errorCode;
    private String itemName;

    @Setup
    public void setup() {
        FlexibleAttributeError[] errors = FlexibleAttributeError.values();
        errorCode = Stream.of(errors).collect(Collectors.toMap(Enum::name, v -> v));
        int ordinal = ThreadLocalRandom.current().nextInt(errors.length);
        itemName = errors[ordinal].name();
    }

    @Benchmark
    public void testValueOf(Blackhole bh) {
        Enum error = FlexibleAttributeError.valueOf(itemName);
        bh.consume(error);
    }

    @Benchmark
    public void testEnumValuesToMapGet(Blackhole bh) {
        Enum error = errorCode.get(itemName);
        bh.consume(error);
    }
}
