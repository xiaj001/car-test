package com.aisino.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import java.util.concurrent.CountDownLatch;

/**
 * @author: xiajun003
 * @Date: 2019/12/18 21:21
 * @Description:
 */
public class ZkTest implements Watcher{

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws Exception {

        String connectStr = "127.0.0.1:2181";
        ZooKeeper zooKeeper = new ZooKeeper(connectStr, 5000, new ZkTest());
        System.err.println(zooKeeper.getState());
        countDownLatch.await();
        System.err.println(zooKeeper.getState());
    }

    @Override
    public void process(WatchedEvent event) {
        System.err.println("receive watch event : " + event);
        if (Event.KeeperState.SyncConnected == event.getState()){
            countDownLatch.countDown();
        }

    }
}
