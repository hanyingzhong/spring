package com.myconcurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * @author �����-2015��6��19�� RecursiveTask����û�з���ֵ������
 */
class SumTask extends RecursiveTask<Integer> {
	// ÿ��"С����"���ֻ��ӡ70����
	private static final int MAX = 70;
	private int arr[];
	private int start;
	private int end;

	SumTask(int arr[], int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// ��end-start��ֵС��MAXʱ�򣬿�ʼ��ӡ
		if ((end - start) < MAX) {
			for (int i = start; i < end; i++) {
				sum += arr[i];
			}
			return sum;
		} else {
			System.err.println("=====����ֽ�======");
			// ��������ֽ������С����
			int middle = (start + end) / 2;
			SumTask left = new SumTask(arr, start, middle);
			SumTask right = new SumTask(arr, middle, end);
			// ����ִ������С����
			left.fork();
			right.fork();
			// ������С�����ۼӵĽ���ϲ�����
			return left.join() + right.join();
		}
	}

}

public class ForkJoinPoolTest2 {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int arr[] = new int[1000];
		Random random = new Random();
		int total = 0;
		// ��ʼ��100������Ԫ��
		for (int i = 0; i < arr.length; i++) {
			int temp = random.nextInt(100);
			// ������Ԫ�ظ�ֵ,��������Ԫ�ص�ֵ��ӵ�total�ܺ���
			total += (arr[i] = temp);
		}
		System.out.println("��ʼ��ʱ���ܺ�=" + total);
		// ��������Runtime.getRuntime().availableProcessors()����ֵ��Ϊ�����Ĳ����̵߳�ForkJoinPool
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// �ύ�ɷֽ��PrintTask����
		Future<Integer> future = forkJoinPool.submit(new SumTask(arr, 0, arr.length));
		System.out.println("����������ܺ�=" + future.get());
		// �ر��̳߳�
		forkJoinPool.shutdown();
	}

}
