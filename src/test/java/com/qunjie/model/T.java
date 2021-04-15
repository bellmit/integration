package com.qunjie.model;/**
 * Created by whs on 2020/12/14.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright (C),2020-2020,群杰印章物联网
 * FileName: com.qunjie.model.T
 *
 * @author whs
 *         Date:   2020/12/14  9:43
 *         Description:
 *         History:
 *         &lt;author&gt;    &lt;time&gt;  &lt;version&gt;  &lt;desc&gt;
 *         修改人姓名           修改时间           版本号          描述
 */
public class T {
    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

    }

    private static void t( List list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            if (i == 2)
                return;
        }
    }

    private static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = new CopyOnWriteArrayList<>();
        //List<Integer> list = Collections.synchronizedList(l);
        List<Integer> integerList = new ArrayList<>();

        AtomicBoolean flag = new AtomicBoolean(true);
        for (int i = 0;i < 10000; i++){
            integerList.add(i);
        }
        for (Integer i : integerList) {

            int finalI = i;
            poolExecutor.execute(() ->{
                System.out.println(Thread.currentThread().getName()+"----"+finalI);
                list.add(finalI);
                if (finalI == 8){
                    flag.set(false);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("========================================="+list.size());
        list.sort((o1,o2)->o1-o2>0?1:-1);
        System.out.println("================list========================="+list);
        System.out.println("================flag========================="+flag.get());
    }

    private static void test2() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        AtomicBoolean flag = new AtomicBoolean(true);
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"----"+finalI);
                list.add(finalI);
                if (finalI == 8){
                    flag.set(false);
                }
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("============================="+list.size());
        System.out.println("============================="+flag.get());
    }

    private static void test3() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
           if (i == 34)
               return;
        }
        System.out.println("========================");
    }
}
