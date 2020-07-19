package com.codeHub.service;

import org.springframework.stereotype.Component;

@Component
public class ConcurrencyService {
    public void multithreadCmd(){
        Printer p=new Printer();
        p.setName("thread-printer1");
        p.start();
        try {
            Thread.sleep(2000);
            p.join();//wait for p1 to finish
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Printer p2=new Printer();
        p2.start();
        p2.setName("thread-printer2");

        Printer p3=new Printer();
        p3.start();
        p3.setName("thread-printer3");

        Writer writer=new Writer();
        Thread thread=new Thread(writer);
        thread.setName("thread-writer");
        thread.start();
    }
    class Printer extends Thread{
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Index: "+i+" Thread: Wowowowowow. " + Thread.currentThread().getName());
            }
        }
    }
    class Writer implements Runnable{
        public void run(){
            for(int i=0;i<5;i++)
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            System.out.println("Runnable:Wowowowowow "+Thread.currentThread().getName());
        }
    }
}
