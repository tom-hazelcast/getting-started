package com.hazelcast.tao.gettingstarted.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;

public class MyEntryAddedListener implements EntryAddedListener<String, String>
	{
		private final static Logger l = LoggerFactory.getLogger(MyEntryAddedListener.class);

		@Override
		public void entryAdded(EntryEvent<String, String> event)
			{
				String whoAmI = event.getMember().getSocketAddress().getHostName() + ":"
				    + event.getMember().getSocketAddress().getPort();
				l.info("member: {}: added: {} - {}", whoAmI, event.getKey(), event.getValue());

			}

	}
