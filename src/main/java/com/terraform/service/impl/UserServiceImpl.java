package com.terraform.service.impl;

import com.terraform.model.SysUser;
import com.terraform.repository.SysUserMapper;
import com.terraform.service.UserService;
import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RBucket;
//import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

//    @Autowired
//    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    @Autowired
    private SysUserMapper sysUserMapper;

    private String redisKey1 = "REDIS_KEY_1";

    private String redisKey2 = "REDIS_KEY_2";

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public List<String> getCacheListByRedission() {

//        List<String> list = Arrays.asList("Redission-1","Redission-2");
//
//        RBucket<List<String>> bucket = null;
//        bucket = redissonClient.getBucket(redisKey1);
//        if (bucket.isExists()) {
//            return bucket.get();
//        }
//        bucket.set(list, 100, TimeUnit.SECONDS);
//        return bucket.get();
        return null;
    }

    @Override
    public List<String> getCacheListByRedis() {

        List<String> list = Arrays.asList("Redis-1","Redis-2");

        Object result = getObject(redisKey2);
        if(isNull(result)) {
            putObject(redisKey2, list);
            log.info("get Data from no cache-------------------------------------");
            return list;
        } else {
            log.info("get Data from cache-------------------------------------");
            return (List<String>)(result);
        }
    }

    private void putObject(Object key, Object value) {
        if (value != null) {
            // 向Redis中添加数据，有效时间是2天
            redisTemplate.opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
        }
    }

    private Object getObject(Object key) {
        try {
            if (key != null) {
                Object obj = redisTemplate.opsForValue().get(key.toString());
                return obj;
            }
        } catch (Exception e) {
            log.error("redis ",e);
        }
        return null;
    }
}
