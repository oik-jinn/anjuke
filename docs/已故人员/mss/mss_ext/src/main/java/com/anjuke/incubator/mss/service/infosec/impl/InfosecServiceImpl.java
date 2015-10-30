package com.anjuke.incubator.mss.service.infosec.impl;

import static com.anjuke.incubator.mss.common.Const.ID;
import static com.anjuke.incubator.mss.common.Const.SOURCE;
import static com.anjuke.incubator.mss.common.Const.TYPE;
import static com.anjuke.incubator.mss.common.Const.infoSecMap;
import static com.anjuke.incubator.mss.common.Utils.plainToMap;

import java.net.URISyntaxException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anjuke.incubator.mss.service.infosec.InfosecService;
import com.anjuke.incubator.mss.service.storage.RedisService;
import com.google.common.collect.ImmutableMap;

@Service
public class InfosecServiceImpl implements InfosecService {
    private static final Logger _logger =
        LoggerFactory.getLogger(InfosecServiceImpl.class);

    private static String _infosec =
        "http://infosec.a.ajkdns.com/infosec/dict/download";

    private static String lastModifiedString = "last-modified";

    @Resource
    RedisService redis;

    public void fetchBanwordsToRedis(String dict) throws URISyntaxException {
        Map<String, String> dictInfo = infoSecMap.getOrDefault(dict, null);
        if(dictInfo != null){

            _logger.info("Fetching {} from Infosec", dict);

            Map<String, String> map = fetch(dict, dictInfo);
            if(map != null){
                redis.delete(dict);
                redis.setKeyValue(dict, map);

                _logger.info("{} has {} keywords", dict, redis.hlen(dict));
            }
        }
    }

    public Map<String, String> fetch(String dict, Map<String, String> params)
            throws URISyntaxException {

        ResponseEntity<String> response = new RestTemplate().exchange(
                getBuilder(dict, params).build(),
                HttpMethod.GET,
                new HttpEntity<String>(getHeaders(dict)),
                String.class);

        if(response.getStatusCode().is2xxSuccessful()){

            setLastModified(dict, response.getHeaders().getLastModified());

            /* Banwords need type */
            return plainToMap(response.getBody(), params.get(TYPE));

        } else {

            if(response.getStatusCode().is3xxRedirection()){

                _logger.info("Dict {} Not Changed", dict);

            } else {

                _logger.info("Something wrong while fetching {} from infosec", dict);
            }

            return null;
        }
    }

    public URIBuilder getBuilder(String url, Map<String, String> params)
            throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(_infosec);

        /**
         * params: ?type=11&source=1
         * Informations Map in com.anjuke.incubator.mss.common.Const
         */
        uriBuilder.setParameter(TYPE, String.valueOf(params.get(ID)));
        uriBuilder.setParameter(SOURCE, String.valueOf(params.get(SOURCE)));

        uriBuilder.getQueryParams();

        return uriBuilder;
    }

    public HttpHeaders getHeaders(String dict) {
        HttpHeaders headers = new HttpHeaders();
        headers.setIfModifiedSince(getLastModified(dict));
        return headers;
    }

    public void setLastModified(String dict, long time) {
        redis.setKeyValue(lastModifiedString,
                ImmutableMap.<String, String> of(dict, String.valueOf(time)));
    }

    public Long getLastModified(String dict){
        if (redis.exists(lastModifiedString)
                && redis.keyExists(lastModifiedString, dict)) {

            String time = redis.getSingleKeyValue(lastModifiedString, dict);
            return Long.parseLong(time);
        } else {
            return 0L;
        }
    }
}
