package com.aisino.concurrent.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author: xiajun003
 * @Date: 2019/8/27 17:18
 * @Description:
 */
public class TestForkJoin {

    public void test(){

        ForkJoinPool executor = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(new int[10000], 0, 9999);

        ForkJoinTask future = executor.submit(task);

        // some time passed...

        if (future.isCompletedAbnormally()) {
            System.out.println(future.getException());
        }

        try {
            System.out.println("result: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    class ArraySumTask extends RecursiveTask<Long> {

        private final int[] array;
        private final int begin;
        private final int end;

        private static final int THRESHOLD = 100;

        ArraySumTask(int[] array, int begin, int end) {
            this.array = array;
            this.begin = begin;
            this.end = end;
        }


        @Override
        protected Long compute() {
            long sum = 0;

            if (end - begin + 1 < THRESHOLD) {      // 小于阈值, 直接计算
                for (int i = begin; i <= end; i++) {
                    sum += array[i];
                }
            } else {
                int middle = (end + begin) / 2;
                ArraySumTask subtask1 = new ArraySumTask(this.array, begin, middle);
                ArraySumTask subtask2 = new ArraySumTask(this.array, middle + 1, end);

                subtask1.fork();
                subtask2.fork();

                long sum1 = subtask1.join();
                long sum2 = subtask2.join();

                sum = sum1 + sum2;
            }
            return sum;
        }
    }
}
