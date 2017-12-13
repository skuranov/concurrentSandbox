package com.uliia.lab.lab1;

import java.util.ArrayList;
import java.util.List;

public class VectorGenerator {
    public List<Double> generate(Integer size) {
        List outList = new ArrayList();
        for (Integer i = 0; i < size; i++) {
            outList.add(Double.parseDouble(i.toString()));
        }
        return outList;
    }
}
