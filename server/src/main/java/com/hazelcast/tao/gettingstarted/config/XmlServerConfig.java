package com.hazelcast.tao.gettingstarted.config;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;

/**
 * Provides server configuration, based on pure java construction. Activated within the 'server' and 'xml-config' profiles.
 * 
 * @author tao
 *
 */
@Configuration
@PropertySource(value =
	{
	    "classpath:hz-server.properties"
	}, ignoreResourceNotFound = true)
@Profile(
	{
	    "server",
	    "xml-config"
	})
@Conditional(AllConfiguredProfilesCondition.class)
public class XmlServerConfig
	{

		private static final Logger l = LoggerFactory.getLogger(XmlServerConfig.class);

		@Value("${xml.config.path}")
		String                      xmlFileName;

		@Bean("xmlFileName")
		@Profile("xml-config")
		public String xmlFileName()
			{
				return xmlFileName;
			}

		@Bean
		@Profile("xml-config")
		public Config config(String xmlPath) throws FileNotFoundException
			{
				l.info("loading config from {}", xmlPath);
				InputStream      xmlStream        = ClassLoader.getSystemResourceAsStream(xmlPath);

				XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(xmlStream);
				Config           config           = xmlConfigBuilder.build();

				return config;
			}

	}
