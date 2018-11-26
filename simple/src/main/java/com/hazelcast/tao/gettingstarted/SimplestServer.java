package com.hazelcast.tao.gettingstarted;

import com.hazelcast.core.Hazelcast;

public class SimplestServer
	{

		public static void main(final String[] args)
			{
				// just create the instance with all defaults and you're done.
				Hazelcast.newHazelcastInstance();
			}

	}
