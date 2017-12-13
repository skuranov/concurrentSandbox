package com.uliia.lab.lab1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Job implements Callable {

    private List<Double> workList;
    private Integer index;
    private Integer complexityFactor;

    Job(List<Double> workList, Integer index, Integer complexityFactor) {
        this.workList = workList;
        this.index = index;
        this.complexityFactor = complexityFactor;
    }

    @Override
    public Object call() throws Exception {
        Long startTime = System.nanoTime();
        workList.stream().map(i -> performComputation(i, complexityFactor)).collect(Collectors.toList());
        return "Thread " + index + " is finished. It has taken " + ((System.nanoTime() - startTime) / 1000000) + "ms";
    }

    private Double performComputation(Double input, Integer complexityFactor) {
        Double temp = input;
        for (int j = 0; j < complexityFactor; j++) {
            temp = Math.pow(temp, 1.789);
        }
        return temp;
    }
}
