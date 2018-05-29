package com.jeromq.router;

import java.util.ArrayList;
import java.util.List;

import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class Worker2 {
	private ZMQ.Context context;
	private ZMQ.Socket socket;
	private List<ZMsg> requests;
	String id;
	
	public Worker2() {
		this.context = ZMQ.context(1);
		this.socket = context.socket(ZMQ.DEALER);
		this.requests = new ArrayList<ZMsg>();
	}

	public Worker2(String id) {
		this.context = ZMQ.context(1);
		this.socket = context.socket(ZMQ.DEALER);
		this.requests = new ArrayList<ZMsg>();
		this.id = id;
	}
	
	public static void main(String[] args) {
		Worker2 worker = new Worker2("worker2");
		worker.start();	
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
