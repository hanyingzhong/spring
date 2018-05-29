package com.jeromq.router2;

import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class GponMgr {
	private ZMQ.Context context;
	private ZMQ.Socket socket;
	private String id;

	public GponMgr(String id) {
		this.context = ZMQ.context(1);
		this.socket = context.socket(ZMQ.DEALER);
		this.id = id;
	}

	public static void main(String[] args) {
		GponMgr client = new GponMgr("client2");
		//client.start();

		GponMgr client1 = new GponMgr("gponmgr");
		client1.start();
	}

	private static int sequence = 0;
	
	public void sendMsg() {
		String now = "hello" + sequence;
		sequence += 1;
		// socket.send(now.getBytes(), 0);

		ZMsg msg = new ZMsg();
		msg.add(now.getBytes());
		msg.addFirst("client1".getBytes()); // 目的2：client1
		msg.addFirst("master".getBytes());  // 目的1：router-slot-2
		msg.send(socket);	
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				socket.connect("ipc://slot2");
				socket.setIdentity(id.getBytes());
				for (int i = 0; i < 1; i++) {
					String now = "hello" + i;
					// socket.send(now.getBytes(), 0);

					ZMsg msg = new ZMsg();
					msg.add(now.getBytes());
					msg.addFirst(id.getBytes()); // source：client1
					msg.addFirst("client1".getBytes()); // 目的2：client1
					msg.addFirst("master".getBytes());  // 目的1：router-slot-2
					msg.send(socket);
				}
				/*
				 * for (int i = 0; i < 5; i++) { String back = new String(socket.recv(0));
				 * System.out.println("recv response is : " + back); }
				 */
				while (true) {
					/*
					 * String back = new String(socket.recv(0));
					 * System.out.println("recv response is : " + back);
					 */
					ZMsg msg = ZMsg.recvMsg(socket);
					System.out.println(id + "-recv: " + msg.getFirst().toString() + " msg:" + msg.getLast().toString());
					//msg.send(socket);
					//sendMsg();
				}

			}

		}).start();
	}
}
