package com.electricexam.controller;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MiaChen
 * @Description:
 * @Date: 2020/6/10 16:21
 * @Version: 1.0
 */
public final class LockTest {
    public static final long currentTime = System.currentTimeMillis();

    public static void f() throws Exception {
        ReentrantLock lock = new ReentrantLock(); // not a fair lock
        lock.lock();
        System.out.println("交付达标上传。。");
        Thread.sleep(20000);
        System.out.println("success");
        lock.unlock();
    }

    public static void test1() throws Exception {
        synchronized (LockTest.class) {
            System.out.println("交付大表上传///");
            Thread.sleep(8000);
            System.out.println("交付大表上传完毕///");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(currentTime);
    }
}
