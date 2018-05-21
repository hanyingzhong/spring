package common.interceptor4;


public interface Interceptor {
	public Object intercept(Invocation invocation)  throws Throwable ;
	public Object register(Object target);
}
