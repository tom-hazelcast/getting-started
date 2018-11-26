package com.hazelcast.tao.gettingstarted.executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

@Component("runnableDemo")
@Profile("client")
public class RunnableDemoCaller implements CommandLineRunner, ApplicationContextAware
	{
		private ApplicationContext applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance client          = (HazelcastInstance) applicationContext.getBean("clientInstance");
				IExecutorService  executorService = client.getExecutorService("default");
				Long              key             = 1l;
				executorService.executeOnKeyOwner(new LoggingRunnable(), key);
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
