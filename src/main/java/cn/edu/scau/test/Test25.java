package cn.edu.scau.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 同步模式之顺序控制
 * 固定运行顺序
 */
@Slf4j(topic = "c.Test25")
public class Test25 {
    static final Object lock = new Object();
    // 表示 t2 是否运行过
    static boolean t2runned = false;
    public static void main(String[] args) {
//        Thread t1 = new Thread(() -> {
//            synchronized (lock) {
//                while (!t2runned) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                log.debug("1");
//            }
//        }, "t1");
//
//        Thread t2 = new Thread(() -> {
//            synchronized (lock) {
//                log.debug("2");
//                t2runned = true;
//                lock.notify();
//            }
//        }, "t2");
//        t1.start();
//        t2.start();

        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        t1.start();

        new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        }, "t2").start();
    }


}
