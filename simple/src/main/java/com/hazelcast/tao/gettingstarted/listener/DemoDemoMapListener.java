package com.hazelcast.tao.gettingstarted.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.MapEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryEvictedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.hazelcast.map.listener.MapClearedListener;
import com.hazelcast.map.listener.MapEvictedListener;

public class DemoDemoMapListener implements EntryAddedListener<String, String>, EntryRemovedListener<String, String>,
		EntryUpdatedListener<String, String>, EntryEvictedListener<String, String>, MapEvictedListener,
		MapClearedListener
	{
		private final static Logger l = LoggerFactory.getLogger(DemoDemoMapListener.class);

		@Override
		public void entryAdded(EntryEvent<String, String> event)
			{
				if (event != null)
					{
						l.info("Entry Added: {} - {}", event.getKey(), event.getValue());
					}
				else
					{
						l.warn("null event");
					}

			}

		@Override
		public void entryRemoved(EntryEvent<String, String> event)
			{
				l.info("Entry Removed: {}", event);
			}

		@Override
		public void entryUpdated(EntryEvent<String, String> event)
			{
				l.info("Entry Updated: {}", event);
			}

		@Override
		public void entryEvicted(EntryEvent<String, String> event)
			{
				l.info("Entry Evicted: {}", event);
			}

		@Override
		public void mapEvicted(MapEvent event)
			{
				l.info("Map Evicted: {}", event);
			}

		@Override
		public void mapCleared(MapEvent event)
			{
				l.info("Map Cleared: {}", event);
			}

	}
