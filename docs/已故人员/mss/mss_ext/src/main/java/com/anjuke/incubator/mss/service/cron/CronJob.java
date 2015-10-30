package com.anjuke.incubator.mss.service.cron;

import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.anjuke.incubator.mss.service.algorithm.Algorithm;
import com.anjuke.incubator.mss.service.infosec.InfosecService;

@Service
public class CronJob {
    private static final Logger _logger =
        LoggerFactory.getLogger(CronJob.class);

    @Resource
    Algorithm algorithm;

    @Resource
    InfosecService infosec;

    @Value("#{'${banwords.dict}'.split(',')}")
    List<String> defaultDicts;

    /**
     * 每个小时都重载内存里的 Trie 结构
     *
     */
    @Scheduled(fixedRate = 60 * 60 * 1000) // 1 hour
    public void reloadAll() {

        _logger.info("Cron: reloading all dicts");

        algorithm.reloadAll();
    }

    /**
     * 每半个小时都尝试从 Infosec 更新违禁词
     *
     * 不用担心，last-modified 会避免大量的数据传输，仅仅是检查有没有更新
     *
     */
    @Scheduled(fixedRate = 30 * 60 * 1000) // 30 minutes
    public void fetchBanwords() throws URISyntaxException {

        _logger.info("Cron: fetching banwords to Redis");

        for(String dict: defaultDicts) {
            infosec.fetchBanwordsToRedis(dict);
        }
    }
}
