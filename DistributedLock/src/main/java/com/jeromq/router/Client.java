package com.jeromq.router;

import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class Client {
	private ZMQ.Context context;
	private ZMQ.Socket socket;
	private String id;

	public Client(String id) {
		this.context = ZMQ.context(1);
		this.socket = context.socket(ZMQ.DEALER);
		this.id = id;
	}

	public static void main(String[] args) {
		Client client = new Client("client2");
		client.start();

		Client client1 = new Client("client1");
		client1.start();

	}

	public void start() {
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				socket.connect("ipc://front");
				socket.setIdentity(id.getBytes());
				for (int i = 0; i < 5; i++) {
					String now = "hello" + i;
					// socket.send(now.getBytes(), 0);

					ZMsg msg = new ZMsg();
					msg.add(now.getBytes());
					msg.addFirst(id.getBytes()); // 源
					msg.addFirst("client1".getBytes()); // 目的
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
					msg.send(socket);
				}

			}

		}).start();
	}
}