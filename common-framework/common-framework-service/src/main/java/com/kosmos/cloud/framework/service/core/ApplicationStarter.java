package com.kosmos.cloud.framework.service.core;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.kosmos.cloud.framework.service.core.config.CoreConfig;
import com.kosmos.cloud.utils.LogbackUtils;

@ComponentScan(value="com.kosmos.cloud")
@EnableAutoConfiguration
public class ApplicationStarter extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    private static volatile ConfigurableApplicationContext instance;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CoreConfig.class);
    }

    public static void run(Class<?> assemblyClass, String[] args) {
    	LogbackUtils.updateSystemPropertyWithLogbackFileName();
    	
        if (instance == null) {
            synchronized (ApplicationStarter.class) {
                if (instance == null) {
                    logger.info("start run a new instance.");

                    instance = new ApplicationStarter()
                        .configure(
                            new SpringApplicationBuilder(ApplicationStarter.class, assemblyClass))
                        .profiles("localResource", "healthCheck").properties(defaultProperties())
                        .run(args);
                } else {
                    logger.warn("instance is not null, can't run a new one.");
                }
            }
        } else {
            logger.warn("instance is not null, can't run a new one.");
        }
    }

    private static Properties defaultProperties() {
        Properties defaultProperties = new Properties();
        // FIXME 下个版本中开放 logback-spring.xml 默认移动到module根路径
        //        defaultProperties.put("logging.config", "file:logback-spring.xml");

        // flyway
        defaultProperties.put("flyway.enabled", "false");
        defaultProperties.put("flyway.locations", "filesystem:migration/db");
        defaultProperties.put("flyway.baseline-on-migrate", "true");
        defaultProperties.put("flyway.baseline-version", "0");
        return defaultProperties;
    }

    public static void close() {
        if (instance != null) {
            logger.info("start stop the instance.");
            instance = null;
            instance.close();
        } else {
            logger.warn("can't close application,instance == null");
        }
    }
    
    public static boolean isRunning() {
        return instance != null;
    }
}
