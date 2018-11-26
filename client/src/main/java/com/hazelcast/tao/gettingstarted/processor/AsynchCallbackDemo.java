package com.hazelcast.tao.gettingstarted.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.ExecutionCallback;

public class AsynchCallbackDemo implements ExecutionCallback<String>
	{
		private final static Logger l = LoggerFactory.getLogger(AsynchCallbackDemo.class);

		@Override
		public void onResponse(String response)
			{
				l.info("processing completed response received for {}", response);
			}

		@Override
		public void onFailure(Throwable t)
			{
				l.error("processing failed: {}", t.getMessage());
			}

	}
