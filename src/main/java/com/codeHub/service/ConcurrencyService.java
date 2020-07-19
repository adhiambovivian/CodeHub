package com.codeHub.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
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
        manulGC();
        executePrintTable();
        runtimeCmd();
        shutDownHook();
        anonymousRunnable();

    }
    class Table{
        //synchronized method
        synchronized public void printTable(int n){
            for(int i=0;i<=5;i++){
                System.out.println(i*n);
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        //synchronized block
        //todo:quota check
        public void printTable2(int n){
            synchronized (this){//synchronized block
                for(int i=0;i<=5;i++){
                System.out.println(i*n);
                try{
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
          }//end synchronized block
        }
    }

    public void executePrintTable(){
        final Table obj=new Table();//only obj
        Thread t1=new Thread(){
            public void run(){
                obj.printTable2(5);
            }
        };
        Thread t2=new Thread(){
            public void run(){
                obj.printTable2(100);
            }
        };

        t1.start();
        t2.start();
    }

    class Printer extends Thread {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Index: " + i + " Thread: Wowowowowow. " + Thread.currentThread().getName());
            }
        }
    }

    public void executeStaticSync(){
        Thread t3=new Thread(){
            public void run(){
                Table2.printTable3(1000);
            }
        };
        Thread t4=new Thread(){
            public void run(){
                Table2.printTable3(200);
            }
        };
        //non-anonymous
        class MyThread1 extends Thread{
            public void run(){
                Table2.printTable3(400);
            }
        }
        MyThread1 non=new MyThread1();
        non.start();
        t3.start();
        t4.start();
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

    public void threadGroup() {
        Writer runnable = new Writer();
        ThreadGroup threadGroup = new ThreadGroup("Parent threadGroup");
        Thread t1 = new Thread(threadGroup, runnable, "one");
        t1.start();
        Thread t2 = new Thread(threadGroup, runnable, "two");
        t2.start();
        Thread t3 = new Thread(threadGroup, runnable, "three");
        t3.start();
        System.out.println("Thread Group Name: " + threadGroup.getName());
        threadGroup.list();

    }

    public void shutDownHook() {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Now main sleeping... press ctrl+c to exit");
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
                System.out.println("shut down hook task completed..");
            }
        });
    }
//todo:survey-send, panel. use runtime with executors
    public void anonymousRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    System.out.println("Anonymous Index********* " + i);
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                    }

                }
                System.out.println("Done processing. Exiting...");
            }
        };
        Runtime runtime=Runtime.getRuntime();
        Thread t1=new Thread(runnable);
        runtime.addShutdownHook(t1);
    }

    public void manulGC(){
        Printer printer=new Printer();
        Writer writer=new Writer();
        printer=null;
        writer=null;
        System.gc();
    }

    public void runtimeCmd(){
        try {
            System.out.println("No. processors: "+Runtime.getRuntime().availableProcessors()+" Total Mem: "+(Runtime.getRuntime().maxMemory()/1E6)+" Free memory: "+(Runtime.getRuntime().freeMemory()/1E6));
           // Runtime.getRuntime().exec("skype");//open skype
            Runtime.getRuntime().exec("shutdown -s -t 0"); //shutdowmn -s switch, -t time delay
            //Runtime.getRuntime().exec("c:\\Windows\\System32\\shutdown -s -t 0");
            Runtime.getRuntime().exec("shutdown -r -t 0");//restart -r

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
class Table2 {
    //static synchronization
    synchronized static void printTable3(int n) {
        for (int i = 0; i <= 5; i++) {
            System.out.println(i * n);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


