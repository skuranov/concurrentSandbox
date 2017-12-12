package com.uliia.lab.lab1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Job implements Callable {

    List<Double> workList;

    Job (List<Double> workList){
        this.workList = workList;
    }

    @Override
    public Object call() throws Exception {
        Long startTime = System.nanoTime();
        workList.stream().map(i -> Math.pow(i, 1.789)).collect(Collectors.toList());
        return System.nanoTime() - startTime;
    }
}
