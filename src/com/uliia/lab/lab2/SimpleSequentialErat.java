package com.uliia.lab.lab2;

import java.util.NavigableSet;

public class SimpleSequentialErat implements Erat {

    @Override
    public void execute(Integer interval, NavigableSet<Integer> simpleNumbers) {
        executeInner(4, interval, simpleNumbers);
    }
}
