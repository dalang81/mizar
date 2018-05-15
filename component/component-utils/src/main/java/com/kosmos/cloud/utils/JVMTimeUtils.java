package com.kosmos.cloud.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class JVMTimeUtils {
    public static long getJVMStartTime(){
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long jvmStartTime = runtimeBean.getStartTime();
        return jvmStartTime;
    }
    
    public static long getJVMUpTime(){
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        long jvmUpTime = runtimeBean.getUptime();
        return jvmUpTime;
    }
}
