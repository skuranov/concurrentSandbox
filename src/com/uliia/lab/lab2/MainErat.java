package com.uliia.lab.lab2;

import com.uliia.lab.lab1.Job;
import com.uliia.lab.lab1.VectorGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainErat {

    private static Integer interval = 100000;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("Greetings %userName%! Welcome to the application! Choose your destiny: " +
                "1 - Sequential algorithm; " +
                "2 - Advanced sequential algorithm; " +
                "3 - Parallel algorithm 1;" +
                "4 - Parallel algorithm 2;" +
                "5 - Parallel algorithm 3;" +
                "6 - Parallel algorithm 4;" +
                "7 - Change timeout;" +
                "8 - Exit");
        try (InputStreamReader is = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(is)) {
            String line = null;
            label:
            while ((line = br.readLine()) != null) {
                switch (line) {
                    case "1":
                        new SimpleSequentialErat().perform(interval);
                        break;
                    case "2":
                        new AdvancedSequentialErat().perform(interval);
                        break;
                    case "3":
                        new DataSplittingConcurrentErat().perform(interval);
                        break;
                    case "4":
                        new SimpleNumbersSplittingConcurrentErat().perform(interval);
                        break;
                    case "7":
                        System.out.println("Please, enter the new timeout......");
                        interval = Integer.parseInt(br.readLine());
                        System.out.println("The timeout has been changed to " + interval);
                        break;
                    case "8":
                        break label;
                    default:
                        System.out.println("Unknown command entered : " + line);
                }
            }
        }
    }
}