package com.jeromq.test;

import org.zeromq.ZMQ;

public class ZMQServer {

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}

	public static class Client extends Thread {
		private String url_worker = "inproc://workers";
		private String url_client = "tcp://127.0.0.1:6666";
		private ZMQ.Poller poller;
		private ZMQ.Context context;
		private ZMQ.Socket clients;
		private ZMQ.Socket workers;

		@SuppressWarnings("deprecation")
		public void run() {
			context = ZMQ.context(1);
			clients = context.socket(ZMQ.ROUTER);
			clients.bind(url_client);

			workers = context.socket(ZMQ.DEALER);
			workers.bind(url_worker);

			for (int i = 0; i < 10; i++) {
				new Worker(context, url_worker).start();
			}

			ZMQ.device(ZMQ.QUEUE, clients, workers);// 开始因这个犯错，搞了半天
			poller = this.context.poller();// 创建一个大小为2的poller
			// 分别将上述的pull注册到poller上，注册的事件是读
			ZMQ.PollItem citem = new ZMQ.PollItem(clients, ZMQ.Poller.POLLIN);
			ZMQ.PollItem witem = new ZMQ.PollItem(workers, ZMQ.Poller.POLLIN);
			poller.register(citem);
			poller.register(witem);
			boolean ok = true;
			while (ok) {
				poller.poll();
				if (poller.getItem(0).isReadable()) {
					System.out.println("当前发送：clients");
					byte[] recv = clients.recv();
					workers.send(recv);
				} else {
					System.out.println("当前发送：workers");
					byte[] recv = workers.recv();
					clients.send(recv);
				}
			}
			closeAll();
		}

		public void closeAll() {
			clients.close();
			workers.close();
			context.term();
		}
	}

	public static class Worker extends Thread {
		private String url_worker1;
		private ZMQ.Context context1;

		public Worker(ZMQ.Context context, String url_worker) {
			this.url_worker1 = url_worker;
			this.context1 = context;
		}

		public void run() {
			ZMQ.Socket socket = context1.socket(ZMQ.REP);
			socket.connect(url_worker1);
			ZMQ.Poller poller = this.context1.poller();;
			ZMQ.PollItem item = new ZMQ.PollItem(socket, ZMQ.Poller.POLLIN);
			poller.register(item);
			while (true) {
				if (poller.poll() == ZMQ.Poller.POLLIN) {
					byte[] recv = socket.recv();
					System.out.println("fix========" + new String(recv));
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					socket.send("world========");
				}
			}
		}
	}
}
