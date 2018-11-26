package com.hazelcast.tao.gettingstarted.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
public class SimpleSpringBootServer implements ApplicationContextAware
	{
		ApplicationContext			applicationContext;

		private static final Logger	l	= LoggerFactory.getLogger(SimpleSpringBootServer.class);

		public static void main(String[] args)
			{
				SpringApplication.run(SimpleSpringBootServer.class, args);
				l.info("after run");
			}

		@Bean
		public HazelcastInstance hazelcastInstance()
			{
				return Hazelcast.newHazelcastInstance();
			}

		@Bean
		public CommandLineRunner commandLineRunner()
			{
				return args -> {
					applicationContext.getBean("hazelcastInstance");
				};
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
