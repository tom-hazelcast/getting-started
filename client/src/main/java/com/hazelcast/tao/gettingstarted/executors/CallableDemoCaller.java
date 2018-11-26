package com.hazelcast.tao.gettingstarted.executors;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;

public class CallableDemoCaller implements CommandLineRunner, ApplicationContextAware
	{
		private final static Logger	l	= LoggerFactory.getLogger(CallableDemoCaller.class);
		private ApplicationContext	applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance client = (HazelcastInstance) applicationContext.getBean("clientInstance");
				IExecutorService executorService = client.getExecutorService("default");

				Callable<String> demoCallable = new DemoCallable();
				l.info("Submitting this one to all members");
				Map<Member, Future<String>> results = executorService.submitToAllMembers(demoCallable);
				for (Future<String> result : results.values())
					{
						l.info("Result from '{}'", result.get());
					}

				executorService.executeOnAllMembers(new LoggingRunnable());
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

	}
