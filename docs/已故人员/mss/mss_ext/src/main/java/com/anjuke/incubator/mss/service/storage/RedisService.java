package com.anjuke.incubator.mss.service.storage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Note: Redis Service 应该被 Spring Data Redis 取代。
 *
 */
public interface RedisService{

    /**
     * Delete Dictionary
     *
     * @param dict
     */
    void delete(String dict);

    /**
     * Check if Dictionary exists
     *
     * @param dict
     */
    Boolean exists(String dict);

    /**
     * Get Dictionary's length
     *
     * @param dict
     */
    Long hlen(String dict);


    /**
     * Set/Get/Delete Keyword and Value from Dictionary
     *
     */
    void setKeyValue(String dict, Map<String, String> keywordMap);

    void delKeyValue(String dict, String keyword);

    String getSingleKeyValue(String dict, String keyword);

    List<String> getMultiKeyValue(String dict, String... keywords);

    /**
     * Check whether Keyword exists in Dictionary
     *
     * @param dict
     * @param keyword
     * @return
     */
    Boolean keyExists(String dict, String keyword);

    /**
     * Get all keywords from HashMap(dict)
     *
     * @param dict
     * @return
     */
    Set<String> getAllKeys(String dict);

}
