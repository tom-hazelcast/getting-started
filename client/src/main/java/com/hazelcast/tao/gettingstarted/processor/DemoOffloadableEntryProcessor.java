package com.hazelcast.tao.gettingstarted.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.Offloadable;
import com.hazelcast.map.AbstractEntryProcessor;

public class DemoOffloadableEntryProcessor extends AbstractEntryProcessor<String, String> implements Offloadable
	{
		private final static Logger	l						= LoggerFactory
				.getLogger(DemoOffloadableEntryProcessor.class);
		private static final long	serialVersionUID		= 1L;
		public final static String	dtFormat				= "HH:mm:ss";
		public final static String	OFFLOADABLE_EXECUTOR	= "default";

		@Override
		public Object process(Entry<String, String> entry)
			{
				String key = entry.getKey();
				String value = entry.getValue();
				l.info("in-place processing called for {}::{}", key, value);

				SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
				String newValue = String.format("This value was modified at %s -- %s", sdf.format(new Date()), value);
				entry.setValue(newValue);

				return newValue;
			}

		@Override
		public String getExecutorName()
			{
				return OFFLOADABLE_EXECUTOR;
			}

	}
