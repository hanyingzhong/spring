package com.jeromq.router;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Request {

	public static void main(String[] args) {
		System.out.println("客户端开始.........");
		Context context = ZMQ.context(1);
		Socket resSocket = context.socket(ZMQ.REQ);// 客服端发送消息
		// resSocket.connect("tcp://115.236.73.253:5570");
		resSocket.connect("ipc://fjs1");
		int i = 0;
		while (i < 5) {
			i++;
			try {
				resSocket.send("hello");
				//resSocket.setReceiveTimeOut(1000);
				byte[] msg = resSocket.recv();
				if (msg == null) {
					System.out.println("wait response expire..");
				}
				else{
					String outputStr = new String(msg);
					System.out.println("#### Client Receive:" + outputStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
