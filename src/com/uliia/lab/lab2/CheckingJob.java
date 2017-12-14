package com.uliia.lab.lab2;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by KuranovSV on 14.12.2017.
 */
public class CheckingJob implements Callable {

    private Integer index;
    private Integer minNum;
    private Integer maxNum;
    private List<Integer> simpleNumbers;

    public CheckingJob(Integer index, Integer minNum, Integer maxNum, List<Integer> simpleNumbers) {
        this.index = index;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.simpleNumbers = simpleNumbers;
    }

    @Override
    public Object call() throws Exception {
        for (int i = minNum; i < maxNum; i++) {
            if (checkIfNumberSimple(i, simpleNumbers)) {
                simpleNumbers.add(i);
            }
        }
        return "Thread " + index + "has ended";
    }

    private Boolean checkIfNumberSimple(Integer num, List<Integer> simpleNumbers) {
        Integer maximumDivider = (int) Math.sqrt(num);
        return simpleNumbers.stream()
                .filter(i -> i <= maximumDivider)
                .noneMatch(i -> num % i == 0);
    }
}
