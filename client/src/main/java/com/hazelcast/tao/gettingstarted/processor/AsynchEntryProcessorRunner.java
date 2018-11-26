package com.hazelcast.tao.gettingstarted.processor;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Component
@Profile("client")
public class AsynchEntryProcessorRunner implements CommandLineRunner, ApplicationContextAware
	{
		private ApplicationContext applicationContext;

		@Override
		public void run(String... args) throws Exception
			{

				HazelcastInstance instance = (HazelcastInstance) applicationContext.getBean("hazelcastInstance");
				IMap<String, String> demo = instance.getMap("demo");

				String key = "someKey";
				demo.set(key, "Just a String value...");

				demo.executeOnKey(key, new DemoEntryProcessor());
			}

		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
			{
				this.applicationContext = applicationContext;
			}

	}
