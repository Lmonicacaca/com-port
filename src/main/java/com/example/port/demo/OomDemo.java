package com.example.port.demo;

public class OomDemo {
    public static void main(String[] args) {

        for(int i=0;i<10000;i++){
            ThreadA thread = new ThreadA();
            thread.start();
        }

        while (true){
            Object o = new Object();
        }
    }
}

class ThreadA extends Thread{
    @Override
    public void run() {
        while (true){
            Object o = new Object();
        }
    }
}
