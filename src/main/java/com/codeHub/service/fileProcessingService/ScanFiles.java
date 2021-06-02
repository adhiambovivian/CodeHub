/* Copyright (C)2021  Vivian */
package com.codeHub.service.fileProcessingService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScanFiles {
    public static void scanDirectories() {
        int maxThreads = 20;
        int waiting = 1; // //time to wait b4 resizing pool
        ThreadPoolExecutor excutorService =
                new ThreadPoolExecutor(
                        maxThreads,
                        maxThreads,
                        waiting,
                        TimeUnit.MINUTES,
                        new ArrayBlockingQueue<Runnable>(maxThreads, true),
                        new ThreadPoolExecutor.CallerRunsPolicy());
        excutorService.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        // task
                    }
                });
        excutorService.shutdown(); // wait for all of the executor threads to finish
        try {
            if (!excutorService.awaitTermination(60, TimeUnit.SECONDS)) {
                // pool didn't terminate after the first try
                excutorService.shutdown();
            }
            if (!excutorService.awaitTermination(60, TimeUnit.SECONDS)) {
                // pool failed to stop after 2nd retry

            }
        } catch (InterruptedException e) {
            e.getStackTrace();
            excutorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
