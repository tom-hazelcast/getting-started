package com.hazelcast.tao.gettingstarted;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
public class SimpleServer
	{

		public static void main(final String[] args)
			{
				SpringApplication.run(SimpleServer.class, args);
			}

		@Bean
		public HazelcastInstance hazelcastInstance()
			{
				// Use the default rules for configuration
				return Hazelcast.newHazelcastInstance();
			}

		@Bean
		public CommandLineRunner commandLineRunner(ApplicationContext context)
			{
				return args -> {
					context.getBean(HazelcastInstance.class);
				};
			}
	}
