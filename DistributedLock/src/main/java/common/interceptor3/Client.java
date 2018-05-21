package common.interceptor3;


public class Client {
	public static void main(String[] args) {
		Target target = new TargetImpl();
		target.execute();
		
		Interceptor interceptor = new Interceptor() {
			public Object intercept(Invocation invocation)  throws Throwable {
				System.out.println("Go Go Go!!!");
				return invocation.proceed();
			}
		};
		target = (Target)TargetProxy.bind(target, interceptor);
		target.execute();
	}
}	
