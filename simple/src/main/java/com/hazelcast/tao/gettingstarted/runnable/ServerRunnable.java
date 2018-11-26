package com.hazelcast.tao.gettingstarted.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;

@Component("server")
@Profile(
	{
			"xserver" // This works with either the 'java-config' or the 'xml-config' profile, so only note the 'server'
						// profile, here.
	})
public class ServerRunnable implements CommandLineRunner, ApplicationContextAware
	{

		private static Logger		l	= LoggerFactory.getLogger(ServerRunnable.class);

		private ApplicationContext	applicationContext;
		private HazelcastInstance	hazelcastInstance;

		@Override
		public void run(String... args) throws Exception
			{

				String name = "hazelcastInstance";
				l.info("instance bean - {}", getApplicationContext().getBean(name));

				l.info("into run, cluster size: {}", getHazelcastInstance().getCluster().getMembers().size());
			}

		/**
		 * @return the applicationContext
		 */
		public ApplicationContext getApplicationContext()
			{
				return applicationContext;
			}

		/**
		 * @param applicationContext
		 *            the applicationContext to set
		 */
		@Override
		public void setApplicationContext(ApplicationContext applicationContext)
			{
				this.applicationContext = applicationContext;
			}

		/**
		 * @return the hazelcastInstance
		 */
		public HazelcastInstance getHazelcastInstance()
			{
				return hazelcastInstance;
			}

		/**
		 * @param hazelcastInstance
		 *            the hazelcastInstance to set
		 */
		public void setHazelcastInstance(HazelcastInstance hazelcastInstance)
			{
				this.hazelcastInstance = hazelcastInstance;
			}

	}
