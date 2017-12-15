package com.uliia.lab.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

public interface Erat {

    default void perform(Integer interval) throws ExecutionException, InterruptedException {
        NavigableSet<Integer> simpleNumbers = new ConcurrentSkipListSet<>();
        simpleNumbers.add(2);
        simpleNumbers.add(3);
        Long startTime = System.currentTimeMillis();
        execute(interval, simpleNumbers);
        System.out.println("The process has ended, " + simpleNumbers.size() +
                " simple numbers were founded. Last number is " + simpleNumbers.last());
        System.out.println("It took " + (System.currentTimeMillis() - startTime) + " ms");
    }

    default void executeInner(Integer firstNumber, Integer interval, Set<Integer> simpleNumbers) {
        for (Integer i = firstNumber; ; i++) {
            if (i >= interval) {
                break;
            } else {
                if (checkIfNumberSimple(i, simpleNumbers)) {
                    simpleNumbers.add(i);
                }
            }
        }
    }

    void execute(Integer interval, NavigableSet<Integer> simpleNumbers) throws ExecutionException, InterruptedException;

    default Boolean checkIfNumberSimple(Integer num, Set<Integer> simpleNumbers) {
        Integer maximumDivider = (int) Math.sqrt(num);
        return simpleNumbers.stream()
                .filter(i -> i <= maximumDivider)
                .noneMatch(i -> num % i == 0);
    }
}
