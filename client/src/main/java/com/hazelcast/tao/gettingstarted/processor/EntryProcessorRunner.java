package com.hazelcast.tao.gettingstarted.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.map.EntryProcessor;

@Component
@Profile("client")
public class EntryProcessorRunner implements CommandLineRunner, ApplicationContextAware
	{
		private final static Logger	l	= LoggerFactory.getLogger(AsynchCallbackDemo.class);
		private ApplicationContext	applicationContext;

		@Override
		public void run(String... args) throws Exception
			{

				HazelcastInstance instance = (HazelcastInstance) applicationContext.getBean("hazelcastInstance");
				IMap<String, String> demoMap = instance.getMap("demo");

				String key = "someKey";
				demoMap.set(key, "Just a String value...");

				EntryProcessor<String, String> asyncProcessor = new DemoOffloadableEntryProcessor();

				demoMap.submitToKey(key, asyncProcessor);
				ExecutionCallback<String> callback = new AsynchCallbackDemo();

				demoMap.submitToKey(key, asyncProcessor, callback);
			}

		public static class AsynchCallbackDemo implements ExecutionCallback<String>
			{

				@Override
				public void onResponse(String response)
					{
						l.info("processing completed response received for {}", response);
					}

				@Override
				public void onFailure(Throwable t)
					{
						l.error("processing failed: {}", t.getMessage());
					}

			}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
			{
				this.applicationContext = applicationContext;
			}

	}
