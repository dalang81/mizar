package com.kosmos.cloud.datasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmos.cloud.datasources.annotation.DataSource;
import com.kosmos.cloud.modules.api.entity.UserEntity;
import com.kosmos.cloud.modules.api.service.UserService;

/**
 * 测试
 * @author kaka
 *  
 * @date 2017/9/16 23:10
 */
@Service
public class DataSourceTestService {
    @Autowired
    private UserService userService;

    public UserEntity queryObject(Long userId){
        return userService.queryObject(userId);
    }

    @DataSource(name = DataSourceNames.SECOND)
    public UserEntity queryObject2(Long userId){
        return userService.queryObject(userId);
    }
}
