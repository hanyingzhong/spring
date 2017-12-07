package com.designmode.dyproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ProxyInterface {
	public void proxyMethod();
}

class TargetObject implements ProxyInterface {

	public void proxyMethod() {
		System.out.println("�ұ������ˣ�������");
	}
}

class ProxyObject implements InvocationHandler {
	// ����Ķ���
	public Object targetObject;

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// ���ã�����һ��Ŀ����󣬺Ͷ�Ӧ�Ķ������
		return method.invoke(targetObject, args);
	}
}

public class ProxyMain {
	public static void main(String[] args) {
		// �����Ŀ�����
		ProxyInterface proxyInterface = new TargetObject();
		// ������
		ProxyObject proxyObject = new ProxyObject();
		proxyObject.setTargetObject(proxyInterface);

		// ���������ת����InvocationHandler
		InvocationHandler handler = proxyObject;
		// ִ�д�������
		Object proxy = Proxy.newProxyInstance(proxyInterface.getClass().getClassLoader(),
				proxyInterface.getClass().getInterfaces(), handler);
		// ת����Ŀ����󣬵���Ŀ�����ķ���
		((ProxyInterface) proxy).proxyMethod();
		
		System.out.println(proxy.getClass().getName());
	}
}
