package com.uliia.lab.lab1;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static Integer threadCount = Runtime.getRuntime().availableProcessors();
    private static Integer vectorSize = 10000000;
    private static Integer complexityFactor = 2;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("Greetings %userName%! Welcome to the application! You have some options: " +
                "1 - Enter the thread count; " +
                "2 - Enter the vector size; " +
                "3 - Enter the complexity factor K; " +
                "4 - Start profiling; " +
                "5 - Exit");
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
                        System.out.println("Please, enter the complexity factor K .....");
                        complexityFactor = Integer.parseInt(br.readLine());
                        System.out.println("Complexity factor K  has been changed to " + complexityFactor);
                        break;
                    case "4":
                        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
                        List<Double> inputVector = new VectorGenerator().generate(vectorSize);
                        List<List<Double>> subLists = new ArrayList<>();
                        int elementsPerThread = vectorSize / threadCount;
                        for(int i = 0; i < threadCount; i++){
                            subLists.add(new ArrayList<>());
                        }
                        for (int i = 0; i < threadCount * elementsPerThread; i++){
                            int cell = (i+1)%threadCount;
                            subLists.get(cell).add(inputVector.get(i));
                        }
                        System.out.println("The profiling has been started");
                        long startTime = System.nanoTime();
                        List<Future<String>> resultList = new ArrayList<>();
                        int threadNumber = 1;
                        for (List<Double> subList : subLists) {
                            Callable callable = new Job(subList, threadNumber++,complexityFactor);
                            resultList.add(executorService.submit(callable));
                        }
                        for (Future<String> stringFuture : resultList) {
                            System.out.println(stringFuture.get());
                        }
                        System.out.println("The job has taken " + (((double) (System.nanoTime() - startTime)) / 1000000) + " ms");
                        break;
                    case "5":
                        break label;
                    default:
                        System.out.println("Unknown command entered : " + line);
                }
            }
        }
    }
}