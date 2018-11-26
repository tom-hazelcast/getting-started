package com.hazelcast.tao.gettingstarted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:hz.properties")
@Configuration
@EnableCaching
public class HzProcess
	{
		public final static String  PROP_NAME        = "spring.profiles.active";
		public final static String  DEFAULT_PROFILES = "server,java-config";
		private final static Logger l                = LoggerFactory.getLogger(HzProcess.class);

		public static void main(final String[] args)
			{
				String profiles;
				profiles = System.getProperty(PROP_NAME);
				if (profiles != null)
					{
						l.info("profiles from system property: {}", profiles);
					}
				if (args.length > 0)
					{
						profiles = args[0];
						l.info("profiles from program arguments: {}", profiles);
					}
				if (profiles == null)
					{
						profiles = DEFAULT_PROFILES;
						l.info("using default profiles: {}", DEFAULT_PROFILES);
					}
				System.setProperty(PROP_NAME, profiles);
				SpringApplication.run(HzProcess.class, args);
			}

		/*
		 * public JoinConfig joinConfig() { JoinConfig config = new JoinConfig(); config.getMulticastConfig().setEnabled(false);
		 * config.getAwsConfig().setEnabled(false); config.getTcpIpConfig().setEnabled(true);
		 * 
		 * return config; }
		 * 
		 * @Bean public Config config() { Config config = new Config();
		 * 
		 * return config; }
		 * 
		 * @Bean public HazelcastInstance hazelcastInstance(Config config) { HazelcastInstance instance =
		 * Hazelcast.newHazelcastInstance(config);
		 * 
		 * return instance; }
		 * 
		 * @Bean public CacheManager cacheManager(final HazelcastInstance instance) { CacheManager manager = new
		 * HazelcastCacheManager(instance);
		 * 
		 * return manager; }
		 */
		@Bean("test-bean")
		@Profile("foo")
		public String fooBean()
			{
				return "this is the foo bean";
			}

		@Bean("test-bean")
		@Profile("bar")
		public String barBean()
			{
				return "this is the bar bean";
			}

		@Bean("other-bean")
		@Profile("other")
		public String otherBean()
			{
				return "this is just some other bean";
			}

		/*
		 * @Bean public CommandLineRunner commandLineRunner(ApplicationContext context) { return args -> { String s = (String)
		 * context.getBean("test-bean"); l.info("got string - {}", s);
		 * 
		 * System.out.println("in main - args len: " + args.length); HazelcastInstance instance = (HazelcastInstance)
		 * context.getBean("hazelcastInstance"); IMap<String, String> map = instance.getMap("map"); map.set("foo", "bar");
		 * l.info("got string - {}", s); System.exit(1); }; }
		 */

	}
