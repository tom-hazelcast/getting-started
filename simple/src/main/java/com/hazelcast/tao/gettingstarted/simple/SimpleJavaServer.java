package com.hazelcast.tao.gettingstarted.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import com.hazelcast.core.LifecycleEvent.LifecycleState;
import com.hazelcast.core.LifecycleListener;
import com.hazelcast.core.LifecycleService;

public class SimpleJavaServer
	{
		private final static Logger l = LoggerFactory.getLogger(SimpleJavaServer.class);

		public static void main(String[] args)
			{
				Object memberLock = new Object();
				Config config = new Config();
				NetworkConfig networkConfig = config.getNetworkConfig();
				JoinConfig joinConfig = networkConfig.getJoin();
				// disable multicast (the default join strategy)
				joinConfig.getMulticastConfig().setEnabled(false);
				// AWS join config is off by default, so this is only illustrative.
				joinConfig.getAwsConfig().setEnabled(false);

				TcpIpConfig tcpIpConfig = joinConfig.getTcpIpConfig();
				tcpIpConfig.setEnabled(true).addMember("127.0.0.1,127.0.0.2,127.0.0.3");

				HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
				LifecycleService service = instance.getLifecycleService();
				service.addLifecycleListener(new MemberLifecycleListener(memberLock));
				synchronized (memberLock)
					{
						try
							{
								memberLock.wait(3000L);
								instance.shutdown();
							}
						catch (Exception e)
							{
								l.error("while waiting for cluster to shut down ({}) : exiting", e.getMessage());
							}

					}
			}

		public static class MemberLifecycleListener implements LifecycleListener
			{
				private Object memberLock;

				public MemberLifecycleListener(Object memberLock)
					{
						super();
						this.memberLock = memberLock;
					}

				@Override
				public void stateChanged(LifecycleEvent event)
					{
						LifecycleState currState = event.getState();
						switch (currState)
							{
								case SHUTTING_DOWN:
									l.warn("cluster is shutting down");

									break;
								case SHUTDOWN:
									l.warn("cluster shutdown complete - will notify member");
									synchronized (memberLock)
										{
											memberLock.notify();
										}

									break;

								default:
									l.info("member state is now: {}", currState);
									break;
							}

					}
			}
	}
