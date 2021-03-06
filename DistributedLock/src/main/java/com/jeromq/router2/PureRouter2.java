package com.jeromq.router2;

import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class PureRouter2 {
	public static class Broker {
		private ZMQ.Socket front;
		private ZMQ.Socket back;
		private ZMQ.Context context;
		String id;

		public Broker(String id) {
			this.id = id;
			this.context = ZMQ.context(1);
			this.front = this.context.socket(ZMQ.ROUTER);
			this.back = this.context.socket(ZMQ.ROUTER);
		}

		public void start() {
			new Thread(new Runnable() {

				public void run() {

					// TODO Auto-generated method stub
					front.setIdentity(id.getBytes());
					front.connect("ipc://front");
					back.bind("ipc://slot2");
					ZMQ.Poller poller = context.poller();
					ZMQ.PollItem fItem = new ZMQ.PollItem(front, ZMQ.Poller.POLLIN);
					ZMQ.PollItem bItem = new ZMQ.PollItem(back, ZMQ.Poller.POLLIN);
					poller.register(fItem);
					poller.register(bItem);

					while (!Thread.currentThread().isInterrupted()) {
						poller.poll();
						if (fItem.isReadable()) {
							ZMsg msg = ZMsg.recvMsg(fItem.getSocket());
							System.out.println("Broker:r-" + msg.getFirst().toString());
							// msg.send(back);
							// must not remove the first FRAME.
							// msg.removeFirst();
							// msg.addFirst("client1".getBytes());
							msg.send(front);
						}
						if (bItem.isReadable()) {
							ZMsg msg = ZMsg.recvMsg(bItem.getSocket());
							System.out.println("back:r-" + msg.getFirst().toString());
							msg.removeFirst();
							if (true == msg.getFirst().toString().equals("master")) {
								msg.send(front);
							} else {
								msg.send(back);
							}
						}
					}
				}
			}).start();
		}
	}

	public static void main(String args[]) {
		Broker broker = new Broker("slot-2");
		broker.start();
	}
}