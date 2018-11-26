package com.hazelcast.tao.gettingstarted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
@EnableAutoConfiguration
public class SimpleClusterClient implements ApplicationContextAware
	{
		private final static Logger	l	= LoggerFactory.getLogger(SimpleClusterClient.class);

		private ApplicationContext	applicationContext;

		public static void main(final String[] args)
			{
				System.setProperty("spring.profiles.active", "client,simplest-client");
				SpringApplication.run(SimpleClusterClient.class, args);
			}

		/*
		 * @Bean
		 * 
		 * @Profile("foo") public CommandLineRunner commandLineRunner(ApplicationContext context) { return args -> {
		 * HazelcastInstance client = (HazelcastInstance) context.getBean("clientInstance");
		 * 
		 * IMap<String, String> demo = client.getMap("demo");
		 * 
		 * String key = "someKey";
		 * 
		 * demo.set(key, "some value");
		 * 
		 * l.info("demo map (size {}) contains value {} for key {}", demo.size(), demo.get(key), key);
		 * System.out.println("in main - args len: " + args.length); }; }
		 */

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
