package com.codeHub;

//public class Threads implements Runnable{
public class Threads extends Thread{
    private Thread thread;
    private String threadName;

    public Threads(String threadName) {
        this.threadName = threadName;
        System.out.println("Thread name::"+threadName);
    }
    @Override
    public void run(){
        System.out.println("Running "+threadName);
        try{
            synchronized (this){//only this block is synchronized
            for(int i=4;i>0;i--) {
                System.out.println("Thread:::" + threadName + " " + i);
                Thread.sleep(50);
            }
            }
        }catch (InterruptedException e){
            System.out.println("Thread " +  threadName + " interrupted.");

        }
        System.out.println("Thread::"+threadName+" exiting");
    }
    @Override
    public void start(){
        System.out.println("Starting "+threadName);
        if(thread==null){
            thread=new Thread(this,threadName);
            thread.start();
        }
    }
}
