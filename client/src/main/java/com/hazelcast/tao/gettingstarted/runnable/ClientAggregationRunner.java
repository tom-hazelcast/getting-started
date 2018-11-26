package com.hazelcast.tao.gettingstarted.runnable;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.tao.gettingstarted.Employee;

@Component
@Profile("client")
public class ClientAggregationRunner implements CommandLineRunner, ApplicationContextAware
	{

		private final static Logger l = LoggerFactory.getLogger(ClientAggregationRunner.class);

		private ApplicationContext  applicationContext;

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance    instance  = applicationContext.getBean(HazelcastInstance.class);
				// get a set of distinct last names.
				IMap<Long, Employee> employees = instance.getMap("employees");
				Set<String>          lastNames = employees.aggregate(Aggregators.distinct("lastName"));
				l.info("found {} distinct last names", lastNames.size());
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
		 *          the applicationContext to set
		 */
		@Override
		public void setApplicationContext(ApplicationContext applicationContext)
			{
				this.applicationContext = applicationContext;
			}

	}
