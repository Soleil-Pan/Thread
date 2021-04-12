package com.pyp.state;

//测试守护线程
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);//默认是false表示是用户线程，正常的线程都是用户线程

        thread.start();

        //用户线程结束
        new Thread(you).start();
    }
}



//上帝
class God implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("上帝保佑着你");
        }
    }
}



//你
class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 3000; i++) {
            System.out.println("开心的活着");
        }
        System.out.println("--------goodbye! world--------");
    }
}
