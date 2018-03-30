package com.myconcurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author lishehe-2015��6��19�� RecursiveAction����û�з���ֵ������
 */
class PrintTask extends RecursiveAction {
	// ÿ��"С����"���ֻ��ӡ20����
	private static final int MAX = 20;

	private int start;
	private int end;

	PrintTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		// ��end-start��ֵС��MAXʱ�򣬿�ʼ��ӡ
		if ((end - start) < MAX) {
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName() + "��iֵ:" + i);
			}
		} else {
			// ��������ֽ������С����
			int middle = (start + end) / 2;
			PrintTask left = new PrintTask(start, middle);
			PrintTask right = new PrintTask(middle, end);
			// ����ִ������С����
			left.fork();
			right.fork();
		}
	}
}

public class ForkJoinPoolTest {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ��������Runtime.getRuntime().availableProcessors()����ֵ��Ϊ�����Ĳ����̵߳�ForkJoinPool
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// �ύ�ɷֽ��PrintTask����
		forkJoinPool.submit(new PrintTask(0, 1000));
		forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);// ������ǰ�߳�ֱ�� ForkJoinPool �����е�����ִ�н���
		// �ر��̳߳�
		forkJoinPool.shutdown();
	}

}