package com.pyp.proxystatic;

//静态代理模式总结：
//1.真实对象和代理对象都要实现同一个接口
//2.代理对象要代理真实角色，传递参数

//优点
    //1.代理对象可以做很多真实对象做不了的事
    //2.真实对象可以专注做自己的事
public class StaticProxy {

    public static void main(String[] args) {

        //You you = new You();
        //you.HappyMarry();

        //WeddingCompany weddingCompany = new WeddingCompany(you);
        //weddingCompany.HappyMarry();

        new Thread( () -> System.out.println("我爱你") ).start();

        new WeddingCompany(new You()).HappyMarry();
    }

}


interface Marry{
    void HappyMarry();
}

//真实角色
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("结婚了");
    }
}

//代理角色
class WeddingCompany implements Marry{

    //真实目标角色
    private Marry target;

    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void HappyMarry() {
         before();
         //真实对象
         this.target.HappyMarry();
         after();
    }

    private void before(){
        System.out.println("结婚前,布置现场");
    }

    private void after(){
        System.out.println("结婚后，收尾款");
    }
}
