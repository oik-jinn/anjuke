package com.anjuke.incubator.mss.service.storage.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.service.storage.RedisService;

/**
 * 根据 Jedis 官方手册，pool.getResource() 返回的 Jedis
 * 是不需要手动 returnResource 的，会在 timeout 后自动回收
 *
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Value("${redis.ip}")
    String redisHost;

    @Value("${redis.port}")
    Integer redisPort;

    @Value("${redis.library}")
    Integer redisPool;

    private static JedisPool pool;

    @PostConstruct
    private void redisInit() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(Boolean.TRUE);
        config.setTestOnReturn(Boolean.TRUE);
        pool = new JedisPool(config, redisHost, redisPort,
                Protocol.DEFAULT_TIMEOUT, null, redisPool);
    }

    private Jedis initJedis() {
        Jedis jedis = pool.getResource();
        return jedis;
    }

    @Override
    public void delete(String dict) {
        try(Jedis jedis = initJedis()) {
            jedis.del(dict);
        }
    }

    @Override
    public Boolean exists(String dict) {
        try(Jedis jedis = initJedis()) {
            Boolean exist = jedis.exists(dict);
            return exist;
        }
    }

    @Override
    public void setKeyValue(String dict, Map<String, String> keyValue) {
        try(Jedis jedis = initJedis()) {
            jedis.hmset(dict, keyValue);
        }
    }

    @Override
    public void delKeyValue(String dict, String keyword) {
        try(Jedis jedis = initJedis()) {
            jedis.hdel(dict, keyword);
        }
    }

    @Override
    public String getSingleKeyValue(String dict, String keyword) {
        try(Jedis jedis = initJedis()) {
            String keyValue = jedis.hget(dict, keyword);
            return keyValue;
        }
    }

    @Override
    public List<String> getMultiKeyValue(String dict, String... keywords) {
        try(Jedis jedis = initJedis()) {
            List<String> keyValues = jedis.hmget(dict, keywords);
            return keyValues;
        }
    }

    @Override
    public Boolean keyExists(String dict, String keyword) {
        try(Jedis jedis = initJedis()) {
            Boolean exist = jedis.hexists(dict, keyword);
            return exist;
        }
    }

    @Override
    public Set<String> getAllKeys(String dict) {
        try(Jedis jedis = initJedis()) {
            Set<String> allKeys = jedis.hkeys(dict);
            return allKeys;
        }
    }

    @Override
    public Long hlen(String dict) {
        try(Jedis jedis = initJedis()) {
            Long len= jedis.hlen(dict);
            return len;
        }
    }

}
