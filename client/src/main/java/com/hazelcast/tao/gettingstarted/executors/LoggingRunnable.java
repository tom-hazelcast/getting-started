package com.hazelcast.tao.gettingstarted.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

@Component("loggingRunnable")
public class LoggingRunnable implements Runnable, HazelcastInstanceAware
	{

		private static Logger		l	= LoggerFactory.getLogger(LoggingRunnable.class);

		private HazelcastInstance	hazelcastInstance;

		@Override
		public void run()
			{
				l.info("into run, cluster size: {}", getHazelcastInstance().getCluster().getMembers().size());
			}

		public HazelcastInstance getHazelcastInstance()
			{
				return hazelcastInstance;
			}

		/**
		 * @param hazelcastInstance
		 *            the hazelcastInstance to set
		 */
		@Override
		public void setHazelcastInstance(HazelcastInstance hazelcastInstance)
			{
				this.hazelcastInstance = hazelcastInstance;
			}

	}
