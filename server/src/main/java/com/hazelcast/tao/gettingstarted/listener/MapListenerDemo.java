package com.hazelcast.tao.gettingstarted.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Component
@Profile("server")
public class MapListenerDemo implements CommandLineRunner, ApplicationContextAware
	{
		private final static Logger l = LoggerFactory.getLogger(MapListenerDemo.class);

		private ApplicationContext  applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance    instance  = applicationContext.getBean(HazelcastInstance.class);
				IMap<String, String> employees = instance.getMap("employees");
				employees.addLocalEntryListener(new MyEntryAddedListener());
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
		 *          the applicationContext to set
		 */
		@Override
		public void setApplicationContext(ApplicationContext applicationContext)
			{
				this.applicationContext = applicationContext;
			}

	}
