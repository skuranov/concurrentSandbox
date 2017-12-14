package com.uliia.lab.lab2;

import java.util.List;

public class AdvancedSequentialErat implements Erat {

    @Override
    public Boolean checkIfNumberSimple(Integer num, List<Integer> simpleNumbers) {
        Integer maximumDivider = (int) Math.sqrt(num);
        return simpleNumbers.stream()
                .filter(i -> i <= maximumDivider)
                .noneMatch(i -> num % i == 0);
    }

    @Override
    public void execute(Integer interval, List<Integer> simpleNumbers) {
        Integer baseInterval = (int) Math.sqrt(interval);
        executeInner(4, baseInterval, simpleNumbers);
        executeInner(baseInterval, interval, simpleNumbers);
    }


}
