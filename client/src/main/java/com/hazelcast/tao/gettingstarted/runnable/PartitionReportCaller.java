package com.hazelcast.tao.gettingstarted.runnable;

import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;
import com.hazelcast.tao.gettingstarted.callable.PartitionReporter;

@Component
@Profile("client")
public class PartitionReportCaller implements CommandLineRunner, ApplicationContextAware
	{

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance            instance        = applicationContext.getBean(HazelcastInstance.class);
				IExecutorService             executorService = instance.getExecutorService("default");

				Map<Member, Future<Integer>> futures         = executorService.submitToAllMembers(new PartitionReporter());
				// process the returned = futures
			}

		ApplicationContext applicationContext;

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
