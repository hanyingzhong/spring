package com.jeromq.router;

import java.util.ArrayList;
import java.util.List;

import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class Worker {
	private ZMQ.Context context;
	private ZMQ.Socket socket;
	private List<ZMsg> requests;
	String id;
	
	public Worker() {
		this.context = ZMQ.context(1);
		this.socket = context.socket(ZMQ.DEALER);
		this.requests = new ArrayList<ZMsg>();
	}

	public Worker(String id) {
		this.context = ZMQ.context(1);
		this.socket = context.socket(ZMQ.DEALER);
		this.requests = new ArrayList<ZMsg>();
		this.id = id;
	}
	
	public static void main(String[] args) {
		Worker worker = new Worker("worker1");
		worker.start();	
		
		Worker worker2 = new Worker("worker2");
		worker2.start();	
	}
	
	public void start() {
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				socket.setIdentity(id.getBytes());
				socket.connect("ipc://back");
				for (int i = 0; i < 5; i++) {
					ZMsg msg = ZMsg.recvMsg(socket);
					requests.add(msg);
					System.out.println(msg.getFirst().toString());
				}
				for (int i = 0; i < 5; i++) {
					ZMsg msg = requests.remove(0);
					ZFrame request = msg.removeLast();

					String now = new String(request.getData());
					System.out.println("recv request : " + now);
					ZFrame out = new ZFrame("world" + i);
					msg.addLast(out);
					msg.send(socket);
				}
			}

		}).start();
	}
}
