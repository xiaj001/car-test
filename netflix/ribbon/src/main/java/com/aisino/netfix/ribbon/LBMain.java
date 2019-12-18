package com.aisino.netfix.ribbon;

import java.util.ArrayList;
import java.util.List;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class LBMain {

	public static void main(String[] args) {
		ILoadBalancer lb = new BaseLoadBalancer();
		List<Server> servers = new ArrayList<Server>();
		servers.add(new Server("localhost", 8080));
		servers.add(new Server("localhost", 8081));
		
		lb.addServers(servers);
		for(int i = 0; i < 10; i++) {
			Server s = lb.chooseServer(null);
			System.out.println(s);
		}
	}

}
