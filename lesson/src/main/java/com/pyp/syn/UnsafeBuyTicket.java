package com.pyp.syn;

//不安全的买票
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station,"小明").start();
        new Thread(station,"小兰").start();
        new Thread(station,"小红").start();

    }
}


class BuyTicket implements Runnable{

    private int ticketNum = 100;
    boolean flag = true;

    @Override
    public void run() {
        //买票
        while (flag){
            try {
                buy();
            } catch (InterruptedException e) {
            }
        }
    }

    private synchronized void buy() throws InterruptedException {
        if(ticketNum<=0){
            return;
        }
        //模拟延时
        Thread.sleep(100);

        //买票
        System.out.println(Thread.currentThread().getName()+"拿到"+ticketNum--);
    }
}