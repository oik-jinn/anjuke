package com.anjuke.incubator.mss.service.algorithm.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.ahocorasick.trie.Trie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.exception.DictNotFoundException;
import com.anjuke.incubator.mss.service.algorithm.Algorithm;
import com.anjuke.incubator.mss.service.storage.RedisService;

@Service
public class AlgorithmImpl implements Algorithm {
    private static final Logger _logger =
        LoggerFactory.getLogger(AlgorithmImpl.class);

    private static Map<String, Trie> trieMap = new ConcurrentHashMap<String, Trie>();

    @Resource
    RedisService redis;

    @Value("#{'${dict}'.split(',')}")
    List<String> defaultDicts;

    /**
     * 当且仅当 trieMap 和 Redis 都有 dict 的时候，才会重载词库
     */
    @Override
    public void reload(String dict) {

        _logger.info("Reloading Dictionary {}", dict);

        if(trieMap.containsKey(dict) && redis.exists(dict)) {
            Trie newTrie = newTrie(redis.getAllKeys(dict));
            trieMap.put(dict, newTrie);

            _logger.info("Reloading Dictionary {} Successful", dict);
        } else {
            _logger.info("Reloading Dictionary {} but not found", dict);
        }
    }

    @Override
    public Boolean create(String dict) {
        if(trieMap.containsKey(dict)) {
            _logger.info("Create Dictionary {} but already exists", dict);

            return Boolean.FALSE;
        } else {
            trieMap.put(dict, newTrie(Const.EmptyMap.keySet()));

            _logger.info("Create Dictionary {} successfully", dict);

            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean delete(String dict) {
        if(trieMap.containsKey(dict)) {
            trieMap.remove(dict);

            _logger.info("Delete Dictionary {} successfully", dict);

            return Boolean.TRUE;
        } else {
            _logger.info("Delete Dictionary {} but not exists", dict);

            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean exists(String dict) {
        return trieMap.containsKey(dict);
    }

    @Override
    public Trie getTrie(String dict) throws Exception {
        Trie trie = trieMap.getOrDefault(dict, null);
        if( trie == null ){
            throw new DictNotFoundException(dict);
        }
        return trie;
    }

    /**
     * 1. 首先检查配置文件中的词典是否被创建了
     *    如果没有创建或者意外删除了，就补全这些词典
     * 2. 重载 trieMap 中的全部词典
     *
     */
    @Override
    public void reloadAll() {
        for(String dict: defaultDicts){
            if(!exists(dict) && redis.exists(dict)){
                create(dict);
            }
        }

        for(String dict: trieMap.keySet()) {
            reload(dict);
        }
    }

    private Trie newTrie(Set<String> keywords) {
        Trie newtrie = new Trie().removeOverlaps();
        for(String keyword : keywords) {
            newtrie.addKeyword(keyword);
        }
        return newtrie;
    }

    @Override
    public Set<String> getDicts() {
        return trieMap.keySet();
    }
}
