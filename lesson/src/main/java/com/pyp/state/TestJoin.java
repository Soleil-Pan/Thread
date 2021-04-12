package com.pyp.state;

//测试join方法
public class TestJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("线程VIP——————" + i);
        }
    }

    public static void main(String[] args) {

        //启动我的线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 500; i++) {
            if(i==200){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main--" + i);
        }
    }

}
