package common.interceptor4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TargetProxy implements InvocationHandler {
	
	private Object target;
	private Interceptor interceptor;
	
	private TargetProxy(Object target, Interceptor interceptor) {
		this.target = target;
		this.interceptor = interceptor;
	}
	
	public static Object bind(Object target, Interceptor interceptor) {
		// 取得代理对象
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new TargetProxy(target, interceptor));
	}
	
	//
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		MethodName methodName = this.interceptor.getClass().getAnnotation(MethodName.class);
/*		if (ObjectUtils.isNull(methodName))
			;*/
		
		String name = methodName.value();
		if (name.equals(method.getName()))
			return interceptor.intercept(new Invocation(target, method, args));
		
		return method.invoke(this.target, args);
	}
}
