package com.codeHub.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConcurrencyService {
    public void multithreadCmd() {
        Printer p = new Printer();
        p.setName("thread-printer1");
        p.start();
        try {
            Thread.sleep(2000);
            p.join();//wait for p1 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Printer p2 = new Printer();
        p2.setPriority(Thread.MIN_PRIORITY);
        p2.start();
        p2.setName("thread-printer2");

        Printer p3 = new Printer();
        p3.start();
        p3.setName("thread-printer3");

        Writer writer = new Writer();
        Thread thread = new Thread(writer);
        thread.setName("thread-writer");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();


        Writer deamonWriter = new Writer();
        Thread daemonThread = new Thread(deamonWriter);
        daemonThread.setName("thread-daemon");
        daemonThread.setDaemon(true);

        threadPool();
        threadGroup();
    }

    class Printer extends Thread {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Index: " + i + " Thread: Wowowowowow. " + Thread.currentThread().getName());
            }
        }
    }

    class Writer implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++)
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.println("Runnable:Wowowowowow " + Thread.currentThread().getName());
        }
    }

    class WorkerThread implements Runnable {
        private String message;

        public WorkerThread(String s) {
            this.message = s;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " (Start) message = " + message);
            processmessage();//call processmessage method that sleeps the thread for 2 seconds
            System.out.println(Thread.currentThread().getName() + " (End)");//prints thread name
        }

        private void processmessage() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        public void threadPool() {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (int i = 0; i <= 10; i++) {
                Runnable worker = new WorkerThread("" + i);
                executorService.execute(worker);
            }
            executorService.shutdown();
            while (!executorService.isTerminated()) {
            }
            System.out.println("Finito");
        }

        public void threadGroup(){
        Writer runnable=new Writer();
        ThreadGroup threadGroup=new ThreadGroup("Parent threadGroup");
        Thread t1=new Thread(threadGroup,runnable,"one");
        t1.start();
        Thread t2=new Thread(threadGroup,runnable,"two");
        t2.start();
        Thread t3=new Thread(threadGroup,runnable,"three");
        t3.start();
        System.out.println("Thread Group Name: "+threadGroup.getName());
        threadGroup.list();

        }
    }


