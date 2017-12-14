package com.uliia.lab.lab2;

import java.util.ArrayList;
import java.util.List;

public class SimpleSequentialErat implements Erat {

    @Override
    public Boolean checkIfNumberSimple(Integer num, List<Integer> simpleNumbers) {
        Integer maximumDivider = (int) Math.sqrt(num);
        return simpleNumbers.stream()
                .filter(i -> i <= maximumDivider)
                .noneMatch(i -> num % i == 0);
    }

    @Override
    public void execute(Integer interval, List<Integer> simpleNumbers) {
        executeInner(4, interval, simpleNumbers);
    }
}
