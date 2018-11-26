package com.hazelcast.tao.gettingstarted.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@Profile("client")
public class ClientConfiguration
	{

		private final static Logger l = LoggerFactory.getLogger(ClientConfiguration.class);

		@Bean
		public ClientConfig clientConfig()
			{
				l.info("into client config");
				ClientConfig clientConfig = new ClientConfig();
				GroupConfig groupConfig = new GroupConfig("dev", "dev-password");

				clientConfig.setGroupConfig(groupConfig);

				ClientNetworkConfig networkConfig = new ClientNetworkConfig();
				networkConfig.addAddress();

				networkConfig
					.addAddress("127.0.0.1:5701", "127.0.0.1:5702", "127.0.0.1:5703")
					.setSmartRouting(true)
					// .addOutboundPortDefinition("34700-34710")
					.setRedoOperation(true)
					.setConnectionTimeout(5000)
					.setConnectionAttemptLimit(5);

				clientConfig.setNetworkConfig(networkConfig);

				return clientConfig;
			}

		@Bean
		public HazelcastInstance clientInstance(ClientConfig clientConfig)
			{
				l.info("into clientInstance");
				try
					{
						// HazelcastInstance clientInstance = HazelcastClient.newHazelcastClient(clientConfig);
						HazelcastInstance clientInstance = HazelcastClient.newHazelcastClient();
						return clientInstance;
					}
				catch (Throwable e)
					{
						l.error("exception: {}", e.getMessage());
						e.printStackTrace();
						throw new RuntimeException(e);
					}

			}

	}
