package com.hazelcast.tao.gettingstarted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class SimpleClient
	{
		private final static Logger l = LoggerFactory.getLogger(SimpleClient.class);

		public static void main(String[] args)
			{
				ClientConfig config = new ClientConfig();
				config.getNetworkConfig().addAddress("127.0.0.1");
				// GroupConfig groupConfig = config.getGroupConfig();
				// groupConfig.setName("dev");
				// groupConfig.setPassword("dev-pass");

				HazelcastInstance client = HazelcastClient.newHazelcastClient(config);

				IMap<String, String> demo = client.getMap("demo");

				String key = "someKey";

				demo.set(key, "some value");

				l.info("demo map (size {}) contains value {} for key {}", demo.size(), demo.get(key), key);
			}
	}
