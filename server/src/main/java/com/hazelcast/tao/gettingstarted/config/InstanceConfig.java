package com.hazelcast.tao.gettingstarted.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@Profile(
	{
			"server",
			"java-config",
			"xml-config"
	})
public class InstanceConfig
	{
		private static final Logger l = LoggerFactory.getLogger(InstanceConfig.class);

		@Bean
		public HazelcastInstance hazelcastInstance(Config config)
			{
				HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
				l.info("config at startup: {}", instance.getConfig().toString());

				return instance;
			}

	}
