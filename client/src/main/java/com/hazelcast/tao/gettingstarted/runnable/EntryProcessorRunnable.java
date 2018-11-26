package com.hazelcast.tao.gettingstarted.runnable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.tao.gettingstarted.Employee;
import com.hazelcast.tao.gettingstarted.processor.DemoEntryProcessor;

@Component
public class EntryProcessorRunnable implements CommandLineRunner, ApplicationContextAware
	{

		private ApplicationContext applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				DemoEntryProcessor   processor = new DemoEntryProcessor();

				Long                 key       = 3l;
				HazelcastInstance    instance  = applicationContext.getBean(HazelcastInstance.class);
				IMap<Long, Employee> employees = instance.getMap("employees");

				employees.executeOnKey(key, processor);
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
