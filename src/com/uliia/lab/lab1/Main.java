package com.uliia.lab.lab1;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static Integer threadCount = 2;
    public static Integer vectorSize = 100;

    public static void main(String[] args) throws IOException {
        System.out.println("Greetings %userNa1me%! Welcome to the application! You have some options: " +
                "1 - Enter the vector size; " +
                "2 - Enter the thread count; " +
                "3 - Start profiling : " +
                "4 - Exit");
        try (InputStreamReader is = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(is)) {
            String line = null;
            label:
            while ((line = br.readLine()) != null) {
                switch (line) {
                    case "1":
                        System.out.println("Please, enter the thread count......");
                        threadCount = Integer.parseInt(br.readLine());
                        System.out.println("Thread count has been changed to " + threadCount);
                        break;
                    case "2":
                        System.out.println("Please, enter the vector size.....");
                        vectorSize = Integer.parseInt(br.readLine());
                        System.out.println("Vector size has been changed to " + vectorSize);
                        break;
                    case "3":
                        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
                        List <Double> inputVector = new VectorGenerator().generate(vectorSize);
                        List <List<Double>> subLists = new ArrayList<>();
                        int elementsPerThread = vectorSize/threadCount;
                        for (int i = 0; i < threadCount; i++)
                        {
                            subLists.add(inputVector.subList(i*elementsPerThread, (i++)*elementsPerThread));
                        }
                        System.out.println("Profiling started at " + System.nanoTime());
                        for (List<Double> subList : subLists) {
                            Callable callable = new Job(subList);
                            Future<String> future = executorService.submit(callable);
                        }
                        System.out.println("Profiling ended at " + System.nanoTime());
                        break;
                    case "4":
                        break label;
                    default:
                        System.out.println("Unknown command entered : " + line);
                }
            }
        }
    }
}