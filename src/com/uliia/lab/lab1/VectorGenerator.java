package com.uliia.lab.lab1;

import java.util.ArrayList;
import java.util.List;

public class VectorGenerator {
    List<Double> generate(Integer size) {
        List outList = new ArrayList();
        for (Integer i = 1; i < size; i++) {
            outList.add(i);
        }
        return outList;
    }
}
