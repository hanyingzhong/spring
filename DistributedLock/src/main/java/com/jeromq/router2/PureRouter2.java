package com.jeromq.router2;

import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class PureRouter2 {
	public static class Broker {
		private ZMQ.Socket front;
		private ZMQ.Context context;
		String id;

		public Broker(String id) {
			this.id = id;
			this.context = ZMQ.context(1);
			this.front = this.context.socket(ZMQ.ROUTER);
		}

		public void start() {
			new Thread(new Runnable() {

				public void run() {

					// TODO Auto-generated method stub
					front.setIdentity(id.getBytes());
					front.connect("ipc://front");
					ZMQ.Poller poller = context.poller();
					ZMQ.PollItem fItem = new ZMQ.PollItem(front, ZMQ.Poller.POLLIN);
					poller.register(fItem);

					while (!Thread.currentThread().isInterrupted()) {
						poller.poll();
						if (fItem.isReadable()) {
							ZMsg msg = ZMsg.recvMsg(fItem.getSocket());
							System.out.println("Broker:r-" + msg.getFirst().toString());
							//msg.send(back);
							//must not remove the first FRAME.
							//msg.removeFirst(); 
							//msg.addFirst("client1".getBytes());
							msg.send(front);
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