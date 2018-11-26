package com.hazelcast.tao.gettingstarted.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

/**
 * this provides a logical 'and' of "spring.profiles.active" values. A configuration class will only be used if all
 * configured profiles are active - i.e. a configuration class may have multiple profiles listed and this class will (when
 * included via @Conditional annotation) cause the configuration class to be activated only if the runtime setting of
 * profile-set spring.profiles.active" contains all (not some) of the profiles.
 * 
 * @author tao
 *
 */
public class AllConfiguredProfilesCondition implements Condition
	{
		private final static Logger l = LoggerFactory.getLogger(AllConfiguredProfilesCondition.class);

		@Override
		public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata typeMetadata)
			{
				if (conditionContext.getEnvironment() != null)
					{

						final MultiValueMap<String, Object> attrs = typeMetadata
							.getAllAnnotationAttributes(Profile.class.getName());
						if (attrs != null)
							{
								for (final Object value : attrs.get("value"))
									{
										final String activeProfiles = conditionContext
											.getEnvironment()
											.getProperty("spring.profiles.active");
										l.info("all active profiles - searching {} for {}", activeProfiles, value);

										for (final String profile : (String[]) value)
											{
												l
													.info("{} contains {}: {}", activeProfiles, profile,
															activeProfiles.contains(profile));
												if (!activeProfiles.contains(profile))
													{
														return false;
													}
											}
									}
								return true;
							}
					}
				return false;
			}

	}
