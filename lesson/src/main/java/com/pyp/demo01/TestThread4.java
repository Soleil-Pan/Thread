package com.pyp.demo01;

//多线程同时操作同一个对象
//买火车票

//多个线程操作同一个资源时，线程不安全，数据紊乱
public class TestThread4 implements Runnable{

    //火车票数
    private int ticketNum = 10;

    @Override
    public void run() {
        while (true){
            if(ticketNum <= 0){
                break;
            }
            //模拟延时
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->拿到了第" + ticketNum-- + "票");
        }
    }


    public static void main(String[] args) {

        TestThread4 ticket = new TestThread4();

        new Thread(ticket,"小明").start();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"小红").start();
    }
}
