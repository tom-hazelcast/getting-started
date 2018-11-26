package com.hazelcast.tao.gettingstarted.interceptor;

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
@Profile("interceptor-client")
public class InterceptorRunner implements CommandLineRunner, ApplicationContextAware
	{
		private final static Logger	l	= LoggerFactory.getLogger(InterceptorRunner.class);
		private ApplicationContext	applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance client = (HazelcastInstance) applicationContext.getBean("hazelcastInstance");
				IMap<String, String> employees = client.getMap("employees");
				employees.addInterceptor(new DemoMapInterceptor());

				// toss a little dummy data in, to see it execute -
				employees.put("foo", "{\"name\":\"Some name\", \"data\":\"other data would, reasonably, follow\"}");
				employees.put("bar", "{\"name\":\"Some other name\\\", \\\"data\\\":\\\"with different other data \"}");
				employees.put("baz", "{\"name\":\"More names\\\", \\\"data\\\":\\\"more data\"}");
				l.info("getting 'foo' - {}", employees.get("foo"));
				l.info("removing 'foo' - {}", employees.remove("foo"));
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
