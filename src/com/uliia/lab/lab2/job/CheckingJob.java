package com.uliia.lab.lab2.job;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by KuranovSV on 14.12.2017.
 */
public class CheckingJob implements Callable {

    private Integer index;
    private Integer minNum;
    private Integer maxNum;
    private Set<Integer> simpleNumbers;
    private Set<Integer> simpleNumbersOrig;

    public CheckingJob(Integer index, Integer minNum, Integer maxNum,
                       Set<Integer> simpleNumbersOrig, Set<Integer> simpleNumbers) {
        this.index = index;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.simpleNumbers = simpleNumbers;
        this.simpleNumbersOrig = simpleNumbersOrig;
    }

    @Override
    public Object call() throws Exception {
        for (int i = minNum; i < maxNum; i++) {
            if (checkIfNumberSimple(i, simpleNumbers)) {
                simpleNumbersOrig.add(i);
            }
        }
        return index;
    }

    private Boolean checkIfNumberSimple(Integer num, Set<Integer> simpleNumbers) {
        Integer maximumDivider = (int) Math.sqrt(num);
        return simpleNumbers.stream()
                .filter(i -> i <= maximumDivider)
                .noneMatch(i -> num % i == 0);
    }
}
