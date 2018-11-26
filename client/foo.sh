#!/bin/bash

REPO=/home/tao/.m2/repository;
SPRING=${REPO}/org/springframework;
HZP=${REPO}/com/hazelcast;
CP=/media/sf_projects/finish-ws/source/getting-started/client/target/classes:\
${HZP}/imdg/cloud/common/0.0.2-SNAPSHOT/common-0.0.2-SNAPSHOT.jar:\
${SPRING}/boot/spring-boot-starter/2.0.4.RELEASE/spring-boot-starter-2.0.4.RELEASE.jar:\
${SPRING}/boot/spring-boot/2.0.4.RELEASE/spring-boot-2.0.4.RELEASE.jar:\
${SPRING}/spring-context/5.0.8.RELEASE/spring-context-5.0.8.RELEASE.jar:\
${SPRING}/spring-aop/5.0.8.RELEASE/spring-aop-5.0.8.RELEASE.jar:\
${SPRING}/spring-beans/5.0.8.RELEASE/spring-beans-5.0.8.RELEASE.jar:\
${SPRING}/spring-expression/5.0.8.RELEASE/spring-expression-5.0.8.RELEASE.jar:\
${SPRING}/boot/spring-boot-autoconfigure/2.0.4.RELEASE/spring-boot-autoconfigure-2.0.4.RELEASE.jar:\
${SPRING}/boot/spring-boot-starter-logging/2.0.4.RELEASE/spring-boot-starter-logging-2.0.4.RELEASE.jar:\
${REPO}/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:\
${REPO}/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:\
${REPO}/org/apache/logging/log4j/log4j-to-slf4j/2.10.0/log4j-to-slf4j-2.10.0.jar:\
${REPO}/org/apache/logging/log4j/log4j-api/2.10.0/log4j-api-2.10.0.jar:\
${REPO}/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.jar:\
${REPO}/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:\
${SPRING}/spring-core/5.0.8.RELEASE/spring-core-5.0.8.RELEASE.jar:\
${SPRING}/spring-jcl/5.0.8.RELEASE/spring-jcl-5.0.8.RELEASE.jar:\
${REPO}/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar:\
${HZP}/hazelcast-client/3.10.4/hazelcast-client-3.10.4.jar:\
${HZP}/hazelcast/3.10.4/hazelcast-3.10.4.jar: 

set -x
find . -type f -a -name Cli\*java | xargs 	\
	javac -d /media/sf_projects/finish-ws/source/getting-started/client/target/classes 	\
		-classpath ${CP}	\
		-sourcepath /media/sf_projects/finish-ws/source/getting-started/client/src/main/java 2>&1
