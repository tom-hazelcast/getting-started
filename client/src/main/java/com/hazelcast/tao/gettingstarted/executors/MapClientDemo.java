package com.hazelcast.tao.gettingstarted.executors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;

public class MapClientDemo
	{

		private final static Logger l = LoggerFactory.getLogger(MapClientDemo.class);

		private HazelcastInstance   hazelcastInstance;

		public void oldMethod()
			{
				Map<String, String> myMap = new ConcurrentHashMap<>();
				String              key   = "someKey";
				String              value = "Just a randome value";

				myMap.put(key, value);

				l.info("getting key {} from in-process map yields value: {}", key, myMap.get(key));
			}

		public void newMethod()
			{
				// ?? shouldn't this be 'IMap'? No - Hazelcast IMaps inherit from collections maps.
				Map<String, String> myMap = hazelcastInstance.getMap("myMap");

				String              key   = "someKey";
				String              value = "Just a randome value";

				myMap.put(key, value);

				l.info("getting key {} from the Hazelcast map yields value: {}", key, myMap.get(key));
			}
	}
