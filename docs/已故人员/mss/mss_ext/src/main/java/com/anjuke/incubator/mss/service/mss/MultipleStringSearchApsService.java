package com.anjuke.incubator.mss.service.mss;

import com.anjuke.aps.spring.ApsMethod;
import com.anjuke.aps.spring.ApsModule;

@ApsModule(name = "mss")
public interface MultipleStringSearchApsService {

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "match")
    Object match(String dict, String text) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "search")
    Object search(String dict, String text) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "multiMatch")
    Object multiMatch(String dict, String[] texts) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "multiSearch")
    Object multiSearch(String dict, String[] texts) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "multiDictMatch")
    Object multiDictMatch(String[] dict, String texts) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "multiDictSearch")
    Object multiDictSearch(String[] dict, String texts) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "replace")
    Object replace(String dict, String text, String replacement) throws Exception;

    @ApsMethod(bean = "mss", ingoreBeanName = true, method = "multiReplace")
    Object multiReplace(String dict, String[] texts, String replacement) throws Exception;

}
