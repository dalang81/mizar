package com.kosmos.cloud.utils;

import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;

public class LogbackUtils {
	
	private static Logger log = LoggerFactory.getLogger(LogbackUtils.class);

	public static boolean updateSystemPropertyWithLogbackFileName(){
		try{
			Iterator<Appender<ILoggingEvent>> it = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger("ROOT").iteratorForAppenders();
			while(it.hasNext()){
				Appender<ILoggingEvent> appender = it.next();
				if(appender instanceof AsyncAppender){
					AsyncAppender async = (AsyncAppender)appender;
					Iterator<Appender<ILoggingEvent>> asyncIt = async.iteratorForAppenders();
					while(asyncIt.hasNext()){
						Appender<ILoggingEvent> asyncRef = asyncIt.next();
						if(asyncRef instanceof FileAppender){
							String fileName = ((FileAppender<ILoggingEvent>)asyncRef).getFile();
							System.setProperty("logging.file", fileName);
							return true;
						}
					}
				}else if(appender instanceof FileAppender){
					String fileName = ((FileAppender<ILoggingEvent>)appender).getFile();
					System.setProperty("logging.file", fileName);
					return true;
				}
			}
		}catch(Exception e){
			log.warn("can not init admin log file.", e);
    	}
		
		return false;
	}
}
