package com.pyp.syn;

import java.util.Date;

public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100,"存款");

        Drawing you = new Drawing(account,50,"你");
        Drawing girlfriend = new Drawing(account,50,"girlfriend");

        you.start();
        girlfriend.start();
    }

}

//账户
class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行
class Drawing extends Thread{

    Account account;
    int drawingMoney;
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱

    @Override
    public void run() {

        //锁的对象是变化的量，需要增删改查的对象
        synchronized (account){

            //判断有没有钱
            if(account.money-drawingMoney <= 0){
                System.out.println(Thread.currentThread().getName() + "钱不够，取钱失败");
                return;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money = account.money-drawingMoney;
            nowMoney = nowMoney + drawingMoney;

            System.out.println(account.name + "余额为：" + account.money);
            System.out.println(this.getName()+"手里的钱"+nowMoney);
        }
    }
}