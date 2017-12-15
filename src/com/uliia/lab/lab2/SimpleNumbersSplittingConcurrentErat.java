package com.uliia.lab.lab2;

import com.uliia.lab.lab2.job.CheckingJob;

import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.*;

/**
 * Created by KuranovSV on 14.12.2017.
 */
public class SimpleNumbersSplittingConcurrentErat implements Erat {
    @Override
    public void execute(Integer interval, NavigableSet<Integer> simpleNumbers) throws ExecutionException, InterruptedException {
        Integer threadCount = Runtime.getRuntime().availableProcessors();
        Integer baseInterval = (int) Math.sqrt(interval);
        executeInner(4, baseInterval, simpleNumbers);
        Integer simpleNumbersPerThread = simpleNumbers.size() / threadCount;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<Integer>> resultList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Callable callable = new CheckingJob(i, baseInterval, interval, simpleNumbers,
                    simpleNumbers.subSet(i * simpleNumbersPerThread, (i + 1) * simpleNumbersPerThread));
            resultList.add(executorService.submit(callable));
        }
        for (Future<Integer> integerFuture : resultList) {
            integerFuture.get();
        }
    }


}
