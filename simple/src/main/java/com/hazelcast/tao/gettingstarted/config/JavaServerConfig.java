package com.hazelcast.tao.gettingstarted.config;

//import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Provides server configuration, based on pure java construction. Activated with the 'server' and 'java-config' profiles.
 * The 'and' of the profiles is provided by the 'AllConfiguredProfiles' inclusion in the @Conditional annotation.
 * 
 * @author tao
 *
 */
@Configuration
@Profile(
	{
			"server",
			"java-config"
	})
@Conditional(AllConfiguredProfilesCondition.class)
public class JavaServerConfig
	{
		@Bean
		public JoinConfig joinConfig()
			{
				JoinConfig config = new JoinConfig();
				config.getMulticastConfig().setEnabled(false);
				config.getAwsConfig().setEnabled(false);
				config.getTcpIpConfig().setEnabled(true);
				// take note - this is not real
				config.getTcpIpConfig().addMember("127.0.0.1,127.0.0.2,127.0.0.3");

				return config;
			}

		@Bean
		public NetworkConfig networkConfig(JoinConfig joinConfig)
			{
				NetworkConfig config = new NetworkConfig();

				config.setJoin(joinConfig);

				return config;
			}

		@Bean("config")
		public Config config(NetworkConfig networkConfig)
			{
				Config config = new Config();

				return config;
			}

		@Bean("hazelcastInstance")
		public HazelcastInstance hazelcastInstance(Config config)
			{
				HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

				return instance;
			}

	}
