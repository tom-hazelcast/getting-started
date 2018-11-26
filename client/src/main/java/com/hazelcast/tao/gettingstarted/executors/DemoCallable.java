package com.hazelcast.tao.gettingstarted.executors;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

public class DemoCallable implements Callable<String>, HazelcastInstanceAware, Serializable
	{

		private static final long	serialVersionUID	= 3542452771835441608L;
		private HazelcastInstance	hazelcastInstance;

		@Override
		public String call() throws Exception
			{
				InetSocketAddress socketAddress = hazelcastInstance.getCluster().getLocalMember().getSocketAddress();
				String id = String
					.format("Running in member '%s::%d", socketAddress.getHostName(), socketAddress.getPort());

				return id;
			}

		/**
		 * @return the hazelcastInstance
		 */
		public HazelcastInstance getHazelcastInstance()
			{
				return hazelcastInstance;
			}

		@Override
		public void setHazelcastInstance(HazelcastInstance hazelcastInstance)
			{
				this.hazelcastInstance = hazelcastInstance;
			}

	}
