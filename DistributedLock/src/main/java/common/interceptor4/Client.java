package common.interceptor4;


public class Client {
	public static void main(String[] args) {
		Target target = new TargetImpl();
		target.execute1();
		
		Interceptor interceptor = new InterceptorImpl();
		target = (Target)interceptor.register(target);
		
		target.execute1();
	}
}	
