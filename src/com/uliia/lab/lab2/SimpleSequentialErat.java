package com.uliia.lab.lab2;

import java.util.ArrayList;
import java.util.List;

public class SimpleSequentialErat implements Erat {

    @Override
    public void execute(Integer interval, List<Integer> simpleNumbers) {
        executeInner(4, interval, simpleNumbers);
    }
}
