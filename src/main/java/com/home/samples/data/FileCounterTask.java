package com.home.samples.data;

import java.io.File;
import java.util.concurrent.RecursiveTask;

/**
 * Created by konstantin on 24.03.2019.
 */
public class FileCounterTask extends RecursiveTask<Integer> {
    private File directoryToBypass;

    public FileCounterTask(File directoryToBypass) {
        this.directoryToBypass = directoryToBypass;
    }

    @Override
    protected Integer compute() {
        File[] files = directoryToBypass.listFiles();
        if (files == null) {
            return 0;
        }
        int filesCount = 0;
        for (File file : files) {
            if (file.isFile()) {
                ++filesCount;
                if (file.getName().contains(".java")
                        && (file.getName().contains("Middleware") || file.getName().contains("Block")))
                {
                    System.out.println(file);
                }
            } else {
                filesCount +=new FileCounterTask(file).fork().join();
            }
        }
        return filesCount;
    }
}
