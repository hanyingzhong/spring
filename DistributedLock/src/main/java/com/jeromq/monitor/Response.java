package com.jeromq.monitor;

import org.zeromq.ZMQ;

public class Response {
	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		final ZMQ.Context context = ZMQ.context(1);

		ZMQ.Socket response = context.socket(ZMQ.REP);
		response.bind("tcp://*:5000");
		while (!Thread.currentThread().isInterrupted()) {
			byte[] msg  = response.recv();
			System.out.println(new String(msg));
			
			response.send("hello".getBytes());
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.close();
		context.term();
	}
	
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
