package com.atguigu.jvm;

public class JvmDemo {

    public static void test01(){
       test01();
    }
    public static void test02(){
        System.out.println("22222222222");
    }
    public static void test03(){
        System.out.println("3333333333333");
    }

    public static void main(String[] args) {
        System.out.println("aaaaaaaaaaa");
        test01();
        test03();
        System.out.println("bbbbbbbbbbbb");
    }
}
