package com.uliia.lab.lab2;

import java.util.ArrayList;
import java.util.List;

public class SimpleSequentialErat implements Erat {

    private Long timeout;
    private Long startTime;

    public SimpleSequentialErat(Long timeout) {
        this.timeout = timeout;
    }

    @Override
    public void perform() {
        startTime = System.currentTimeMillis();
        List<Integer> simpleNumbers = new ArrayList<>();
        simpleNumbers.add(2);
        simpleNumbers.add(3);
        while (true) {
            if (checkTimeout()) {
                System.out.println("Sequential process has ended, "+ simpleNumbers.size() +" simple numbers were founded");
                break;
            }
        }
    }

    private Boolean checkTimeout() {
        return startTime + timeout > System.currentTimeMillis();
    }
}
