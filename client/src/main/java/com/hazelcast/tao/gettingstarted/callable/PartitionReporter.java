package com.hazelcast.tao.gettingstarted.callable;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.Member;
import com.hazelcast.core.PartitionService;

public class PartitionReporter implements Callable<Integer>, HazelcastInstanceAware
	{
		private final static Logger l = LoggerFactory.getLogger(PartitionReporter.class);
		private HazelcastInstance   hazelcastInstance;

		@Override
		public Integer call()
			{
				Member           member         = hazelcastInstance.getCluster().getLocalMember();

				String           fmt            = "member listening on %s:%d";
				String           whoAmI         = String
				  .format(fmt, member.getSocketAddress().getHostName(), member.getSocketAddress().getPort());

				PartitionService service        = hazelcastInstance.getPartitionService();
				boolean          memberIsSafe   = service.isLocalMemberSafe();
				String           memberSafety   = memberIsSafe ? "" : "not ";
				boolean          clusterIsSafe  = service.isClusterSafe();
				String           clusterSafety  = clusterIsSafe ? "" : "not ";

				int              partitionCount = service.getPartitions().size();
				fmt = "executing in member @ {} - cluster is {} safe, member is {} safe, hosting {} partitions";
				l.debug(fmt, whoAmI, clusterSafety, memberSafety, partitionCount);

				return Integer.valueOf(partitionCount);
			}
		// <snip> - supporting code

		/**
		 * @return the hazelcastInstance
		 */
		public HazelcastInstance getHazelcastInstance()
			{
				return hazelcastInstance;
			}

		/**
		 * @param hazelcastInstance
		 *          the hazelcastInstance to set
		 */
		@Override
		public void setHazelcastInstance(HazelcastInstance hazelcastInstance)
			{
				this.hazelcastInstance = hazelcastInstance;
			}

	}
