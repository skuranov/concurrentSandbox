package com.uliia.lab.lab2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by KuranovSV on 14.12.2017.
 */
public class DataSplittingConcurrentErat implements Erat {
    @Override
    public void execute(Integer interval, List<Integer> simpleNumbers) {
        Integer threadCount = Runtime.getRuntime().availableProcessors();
        Integer baseInterval = (int) Math.sqrt(interval);
        executeInner(4, baseInterval, simpleNumbers);
        Integer interValPerThread = (interval - baseInterval) / threadCount;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            Callable callable = new CheckingJob(i, baseInterval + (i * interValPerThread),
                    baseInterval + ((i + 1) * interValPerThread), simpleNumbers);
            executorService.submit(callable);
        }
    }


}
