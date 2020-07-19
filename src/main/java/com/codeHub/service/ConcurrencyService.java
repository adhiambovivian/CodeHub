package com.codeHub.service;

import org.springframework.stereotype.Component;

@Component
public class ConcurrencyService {
    public void multithreadCmd(){
        Printer p=new Printer();
        p.start();

        Writer writer=new Writer();
        Thread thread=new Thread(writer);
        thread.start();
    }
    class Printer extends Thread{
        public void run(){
            System.out.println("Thread: Wowowowowow. "+Thread.currentThread().getName());
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
