package com.uliia.lab.lab2;

import java.util.ArrayList;
import java.util.List;

public interface Erat {

    default void perform(Integer interval) {
        List<Integer> simpleNumbers = new ArrayList<>();
        simpleNumbers.add(2);
        simpleNumbers.add(3);
        Long startTime = System.currentTimeMillis();
        execute(interval, simpleNumbers);
        System.out.println("The process has ended, " + simpleNumbers.size() +
                " simple numbers were founded. Last number is " + simpleNumbers.get(simpleNumbers.size() - 1));
        System.out.println("It took " + (System.currentTimeMillis() - startTime) + " ms");
    }

    default void executeInner(Integer firstNumber, Integer interval, List<Integer> simpleNumbers) {
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

    void execute(Integer interval, List<Integer> simpleNumbers);

    default Boolean checkIfNumberSimple(Integer num, List<Integer> simpleNumbers) {
        Integer maximumDivider = (int) Math.sqrt(num);
        return simpleNumbers.stream()
                .filter(i -> i <= maximumDivider)
                .noneMatch(i -> num % i == 0);
    }
}
