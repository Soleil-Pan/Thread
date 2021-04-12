package com.pyp.lambda;

public class TestLambda2 {

    public static void main(String[] args) {

        ILove love = (int a)->{
            System.out.println("i love you-->" + a);
        };

        //  简化1，去掉参数类型
        love = (a)->{
            System.out.println("i love you-->" + a);
        };

        // 简化2,去掉括号
        love = a->{
            System.out.println("i love you-->" + a);
        };

        //简化3,去掉花括号，只有一句代码时
        love = a-> System.out.println("i love you-->" + a);

        /*
        *总结：
        * 1.lambda表达式只能有一行代码的情况下才能简化成一行，如果有多行，用代码块包裹
        * 2.前提是接口为函数式接口
        * 3.多个参数也可以去掉参数，但必须都去掉且要加上括号
        *
        */




        love.love(521);
    }
}


interface ILove{
    void love(int a);
}