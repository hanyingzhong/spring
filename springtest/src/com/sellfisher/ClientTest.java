package com.sellfisher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ClientTest {
    public static void main(String args[]) {
        SellFisher s = new ConcreteSellFisher();
        
        //use InvocationHandler implement dynamical proxy. 
        InvocationHandler p = new ProxySellFisher(s);
        SellFisher obj = (SellFisher)Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), p);
        
        obj.sellFish();
        
        //another method
        ProxySellFisher2 sellFish2 = new ProxySellFisher2(s);
        sellFish2.sellFish();
    }
}
