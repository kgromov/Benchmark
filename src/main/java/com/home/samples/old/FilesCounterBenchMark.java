package com.home.samples.old;

import com.home.samples.data.FileCounterTask;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class FilesCounterBenchMark {

    @Param({"C:\\Projects\\nds_here\\nds-qa", "C:\\Projects\\nds_here\\"})
    private String rootFolderPath;

    private AtomicInteger fileCounter;

    @Setup(Level.Iteration)
    public void setup() {
        fileCounter = new AtomicInteger();
    }

    /*@Benchmark
    public int byPassByRecursiveFileIO(Blackhole bh)
    {
        File[] files = directory.listFiles();
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            if (file.isFile()) {
                filesCount.incrementAndGet();
            } else {
                fileCounter(file, filesCount);
            }
        }
        return filesCount.get();
    }*/

    @Benchmark
    public int byPassByWalkFileNIO() {
        try {
            return Files.walk(Paths.get(rootFolderPath))
                    .filter(Files::isRegularFile)
                    .mapToInt(i -> 1)
                    .sum();
        } catch (IOException e) {
            return -1;
        }
    }

    @Benchmark
    public int byPassByFindFileNIO() {
        try {
            return Files.find(Paths.get(rootFolderPath),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .mapToInt(i -> 1)
                    .sum();
        } catch (IOException e) {
            return -1;
        }
    }

    @Benchmark
    public void byPassByForkJoinTask(Blackhole bh) {
        File rootDirectory = new File(rootFolderPath);
        FileCounterTask task = new FileCounterTask(rootDirectory);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.execute(task);

        /*do {
            System.out.printf("Main: Thread Count:%d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal:%d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism:%d\n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());*/

        pool.shutdown();
        if (task.isCompletedNormally()) {
            System.out.println("Main: The process has completed normally.");
        }
        try {
            System.out.printf("Main: Total files in %s = %d\n", rootDirectory.getAbsolutePath(), task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
