package com.sellfisher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxySellFisher implements InvocationHandler {

    private SellFisher sell;
    
    public ProxySellFisher(SellFisher sell) {
        this.sell = sell;
    }	
	
	@Override
	public Object invoke(Object arg0, Method method, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
        System.out.println("the fish price higher");
        return (Integer)method.invoke(sell, arg2)+10;		
	}

}
