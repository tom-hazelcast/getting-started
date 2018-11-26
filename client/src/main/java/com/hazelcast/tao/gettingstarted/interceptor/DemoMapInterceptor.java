package com.hazelcast.tao.gettingstarted.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.hazelcast.map.MapInterceptor;

@Component
@Profile("interceptor-client")
public class DemoMapInterceptor implements MapInterceptor
	{

		private static final long	serialVersionUID	= -2056917291641302836L;

		@SuppressWarnings("unused")
		private static final Logger	l					= LoggerFactory.getLogger(DemoMapInterceptor.class);

		@Override
		public Object interceptGet(Object value)
			{
				l.info("intercepted 'get' - {}", value);
				// null is a special value here; it means to not change the
				// behavior of the un-intercepted call.
				// just for grins - return a different value
				return String.format("you're getting a changed value - %s", value);
			}

		@Override
		public void afterGet(Object value)
			{
				l.info("this method may be useful for logging, but the stored value is unaltered by anything here.");
			}

		@Override
		public Object interceptPut(Object oldValue, Object newValue)
			{
				if ((System.currentTimeMillis() % 86400000) < (6 * 3600 * 1000))
					{
						throw new RuntimeException("cannot add/change data before 6AM, because of reasons.");
					}
				l.info("put - replacing {} with {}", oldValue, newValue);
				return null;
			}

		@Override
		public void afterPut(Object value)
			{
				l.info("this is called after 'put', so it's not possible to change the put action");
			}

		@Override
		public Object interceptRemove(Object removedValue)
			{
				if ((System.currentTimeMillis() % 86400000) < (6 * 3600 * 1000))
					{
						throw new RuntimeException("cannot remove data before 6AM, because of reasons.");
					}
				return null;
			}

		@Override
		public void afterRemove(Object value)
			{
				l.info("'{}' was removed and it's too late to change that.");
			}

	}
