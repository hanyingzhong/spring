package com.zookeeper.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

/**
 * 与UserinfoBean相关的Watcher
 * 
 * @author JohnGao
 */
@Component
public class UserWatcher implements Watcher {
	@Resource
	private RegisterBean registerBean;
	private ZooKeeper zk_client;
	private String[] paths;
	private Logger logger = LoggerFactory.getLogger(UserWatcher.class);

	public void init(ZooKeeper zk_client, String... paths) {
		this.zk_client = zk_client;
		this.paths = paths.clone();
	}

	
	
	//@Override
	public void process(WatchedEvent event) {
		if (null == zk_client || null == paths)
			return;
		try {
			Thread.sleep(100);
			/* 重新注册�?有节�? */
			for (String path : paths) {
				zk_client.exists(path, this);
			}
			EventType eventType = event.getType();
			switch (eventType) {
			case NodeCreated:
				logger.info("节点-->" + event.getPath() + "被创�?");
				break;
			case NodeDataChanged:
				Map<String, String> maps = new HashMap<String, String>();
				maps.put("username",
						new String(zk_client.getData(paths[0], false, null)));
				maps.put("password",
						new String(zk_client.getData(paths[1], false, null)));
				registerBean.register("userinfoBean", UserinfoBean.class, maps);
				logger.info("节点-->" + event.getPath() + "下的数据发生变化");
				break;
			case NodeChildrenChanged:
				logger.info("节点-->" + event.getPath() + "下的子节点发生变�?");
				break;
			case NodeDeleted:
				logger.info("节点-->" + event.getPath() + "被删�?");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
