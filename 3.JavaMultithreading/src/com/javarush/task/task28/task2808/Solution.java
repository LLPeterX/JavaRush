package com.javarush.task.task28.task2808;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/* 
Осваиваем Callable

Реализуй логику метода getTask, который должен возвращать объект Callable.
Объект Callable должен вычислять сумму всех чисел от 1 до переданного числа i включая его, и возвращать его в виде строки.

*/
public class Solution {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>(); // массив возвр.значений, получ. из getTask()'ов
        ExecutorService executor = Executors.newFixedThreadPool(5); // 5 потокв
        for (int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i))); // 11 потоков
        }

        futures.add(executor.submit(getTask(10000000))); // последний вызов 12-й

        for(Future<String> future : futures) { // выводим результаты всех вызовов
            System.out.println(future.get());
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

/* output
500500
501501
502503
503506
504510
505515
506521
507528
508536
509545
510555
50000005000000
*/
    }

    public static Callable<String> getTask(final int i) {

        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                //return null;
                long sum=0;
                for(int k=1; k<=i; k++)
                    sum+=k;
                return String.valueOf(sum);
            }
        };
        return task;
    }

}
