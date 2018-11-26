package com.hazelcast.tao.gettingstarted;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@SpringBootApplication
@PropertySource("classpath:hz.properties")
@Configuration
@EnableCaching
public class ServerApp
	{

		public static void __main(final String[] args)
			{

				SpringApplication.run(ServerApp.class, args);
			}

		public JoinConfig joinConfig()
			{

				JoinConfig config = new JoinConfig();
				config.getMulticastConfig().setEnabled(false);
				config.getAwsConfig().setEnabled(false);
				config.getTcpIpConfig().setEnabled(true);

				return config;
			}

		@Bean
		public Config config()
			{
				Config config = new Config();

				return config;
			}

		@Bean
		public HazelcastInstance hazelcastInstance(Config config)
			{
				HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

				return instance;
			}

		// @Bean
		// public CacheManager cacheManager(final HazelcastInstance instance)
		// {
		// CacheManager manager = new HazelcastCacheManager(instance);
		//
		// return manager;
		// }
		//
		@Bean
		public CommandLineRunner commandLineRunner(ApplicationContext context)
			{
				return args -> {
					System.out.println("in main - args len: " + args.length);
					HazelcastInstance instance = (HazelcastInstance) context.getBean("hazelcastInstance");
					IMap<String, String> map = instance.getMap("map");
					map.set("foo", "bar");
				};
			}

	}
