package com.uliia.lab.lab2;

import java.util.NavigableSet;

public class AdvancedSequentialErat implements Erat {

    @Override
    public void execute(Integer interval, NavigableSet<Integer> simpleNumbers) {
        Integer baseInterval = (int) Math.sqrt(interval);
        executeInner(4, baseInterval, simpleNumbers);
        executeInner(baseInterval, interval, simpleNumbers);
    }
}
