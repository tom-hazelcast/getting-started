package com.hazelcast.tao.gettingstarted.runnable;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.tao.gettingstarted.listener.DemoDemoMapListener;

//@Component
//@Profile("listener")
public class DemoListenerRunnable implements CommandLineRunner, ApplicationContextAware
	{

		private final static Logger l = LoggerFactory.getLogger(DemoListenerRunnable.class);
		private ApplicationContext  applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				try
					{
						HazelcastInstance    instance = applicationContext.getBean(HazelcastInstance.class);
						IMap<String, String> demo     = instance.getMap("demo");

						demo.addEntryListener(new DemoDemoMapListener(), true);

						for (int i = 0; i < 10; i++)
							{
								String key   = "key:" + i;
								String value = "just some random value: " + i + " @ " + new Date().toString();

								demo.put(key, value);
							}

						System.out.println("ok?");
						Thread.sleep(12000);
						instance.shutdown();
					}
				catch (Exception t)
					{
						l.error("runnable: {}", t.getMessage());
					}

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
