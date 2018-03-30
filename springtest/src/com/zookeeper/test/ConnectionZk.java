package com.zookeeper.test;

import java.util.concurrent.CountDownLatch;
import javax.annotation.Resource;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 与Zookeeper服务器建立session会话
 * 
 * @author JohnGao
 */
public class ConnectionZk {
	@Resource(name = "registerUserNode")
	private RegisterNode registerUserNode;
	@Resource(name = "registerIdNode")
	private RegisterNode registerIdNode;
	private String zk_address;
	private int session_timeout;
	public ZooKeeper zk_client;
	private String usernamePath;
	private String passwordPath;
	private String id;
	private CountDownLatch countDownLatch;
	private Logger logger = LoggerFactory.getLogger(ConnectionZk.class);

	private ConnectionZk(String zk_address, int session_timeout,
			String usernamePath, String passwordPath, String id) {
		this.zk_address = zk_address;
		this.session_timeout = session_timeout;
		this.usernamePath = usernamePath;
		this.passwordPath = passwordPath;
		this.id = id;
		countDownLatch = new CountDownLatch(1);
	}

	/**
	 * 初始化方�?
	 *
	 * @author JohnGao
	 */
	public void init() {
		connection();
	}

	/**
	 * 连接zookeeper
	 * 
	 * @author JohnGao
	 */
	private void connection() {
		try {
			zk_client = new ZooKeeper(zk_address, session_timeout,
					new Watcher() {
						//@Override
						public void process(WatchedEvent event) {
							final KeeperState STATE = event.getState();
							switch (STATE) {
							case SyncConnected:
								countDownLatch.countDown();
								logger.info("成功连接zookeeper服务�?");
								break;
							case Disconnected:
								logger.warn("与zookeeper服务器断�?连接");
								break;
							case Expired:
								logger.error("session会话失效...");
								break;
							default:
								break;
							}
						}
					});
			countDownLatch.await();
			/* 注册与UserinfoBean相关的节�? */
			registerUserNode.register(zk_client, usernamePath, passwordPath);
			/* 注册与IdinfoBean相关的节�? */
			registerIdNode.register(zk_client, id);
			
			//zk_client.create("/testRoot", "testRoot".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);;
			zk_client.create("/info/temp", "1000".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);;
			//zk_client.setData("/testRoot", "1111111111".getBytes(), -1);
			zk_client.setData("/testRoot", "teee".getBytes(), -1, new StatCallback() {
				@Override
				public void processResult(int arg0, String arg1, Object arg2, Stat arg3) {
					System.out.println(arg0 + "===" + arg2);
				}				
			}, "ddddd");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
