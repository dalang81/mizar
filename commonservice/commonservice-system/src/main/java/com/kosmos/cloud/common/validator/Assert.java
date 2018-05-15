package com.kosmos.cloud.common.validator;

import org.apache.commons.lang.StringUtils;

import com.kosmos.cloud.common.exception.RRException;

/**
 * 数据校验
 * @author kaka
 *  
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
