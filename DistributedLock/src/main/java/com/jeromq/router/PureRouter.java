package com.jeromq.router;

import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class PureRouter {
	public static class Broker {
		private ZMQ.Socket front;
		private ZMQ.Socket back;
		private ZMQ.Context context;

		public Broker() {
			this.context = ZMQ.context(1);
			this.front = this.context.socket(ZMQ.ROUTER);
			this.back = this.context.socket(ZMQ.DEALER);
		}

		public void start() {
			new Thread(new Runnable() {

				public void run() {

					// TODO Auto-generated method stub
					front.bind("ipc://front");
					back.bind("ipc://back");
					ZMQ.Poller poller = context.poller();
					ZMQ.PollItem fItem = new ZMQ.PollItem(front, ZMQ.Poller.POLLIN);
					ZMQ.PollItem bItem = new ZMQ.PollItem(back, ZMQ.Poller.POLLIN);
					poller.register(fItem);
					poller.register(bItem);

					while (!Thread.currentThread().isInterrupted()) {
						poller.poll();
						if (fItem.isReadable()) {
							ZMsg msg = ZMsg.recvMsg(fItem.getSocket());
							System.out.println("Broker:r" + msg.getFirst().toString());
							//msg.send(back);
							msg.removeFirst();
							//msg.addFirst("client1".getBytes());
							msg.send(front);
						}
						if (bItem.isReadable()) {
							ZMsg msg = ZMsg.recvMsg(bItem.getSocket());
							System.out.println("Broker:s" + msg.getFirst().toString());
							msg.send(front);
						}
					}
				}

			}).start();
		}
	}

	public static void main(String args[]) {
		Broker broker = new Broker();
		broker.start();
	}
}