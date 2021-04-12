package com.pyp.up;

import com.sun.xml.internal.bind.v2.model.core.ID;

//测试：生产者消费者模型-——>利用缓冲区：管程法
public class TestPC {

    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

//生产者
class Productor extends Thread{
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    //生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Product(i));
            System.out.println("生产了-->"+i);
        }
    }
}


//消费者
class Consumer extends Thread{
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    //消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了---->"+ container.pop().id);
        }
    }
}


//产品
class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }
}


//缓冲区
class SynContainer{

    //容器大小
    Product[] products = new Product[10];
    int count = 0;

    //生产者放入产品
    public synchronized void push(Product product){
        //如果容器满了，等待消费者消费
        if (count == products.length){
            //通知生产者消费
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果容器没满
        products[count] = product;
        count++;

        //通知消费者消费
        this.notifyAll();
    }


    //消费者消费产品
    public synchronized Product pop(){
        //判断能否消费
        if(count == 0){
            //等待生产者生产
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果可以消费
        count--;
        Product product = products[count];

        //通知生产者生产
        this.notifyAll();
        return product;
    }

}