package com.hazelcast.tao.gettingstarted.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Configuration
@Profile("simplest-client")
public class SimpleClientConfiguration
	{
		private final static Logger l = LoggerFactory.getLogger(SimpleClientConfiguration.class);

		@Bean
		public CommandLineRunner commandLineRunner(ApplicationContext context)
			{
				return args -> {
					l.info("into simplest client");
					HazelcastInstance client = (HazelcastInstance) context.getBean("clientInstance");

					IMap<String, String> demo = client.getMap("demo");

					String key = "someKey";

					demo.set(key, "some value");

					l.info("demo map (size {}) contains value {} for key {}", demo.size(), demo.get(key), key);
					System.out.println("in main - args len: " + args.length);
				};
			}

	}
