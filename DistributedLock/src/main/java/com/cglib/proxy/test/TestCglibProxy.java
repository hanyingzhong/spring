package com.cglib.proxy.test;

public class TestCglibProxy {
    public static void main(String args[]){
        CglibProxy proxy = new CglibProxy();

        //动态生成子类的方法创建代理类
        ForumServiceImpl fsi =
                (ForumServiceImpl)proxy.getProxy(ForumServiceImpl.class);

        fsi.removeForum(10);
        fsi.removeTopic(2);
    }
}
