package com.anjuke.incubator.mss.service.mss;

import com.anjuke.incubator.mss.bean.MatchResponseBean;
import com.anjuke.incubator.mss.bean.SearchResponseBean;

public interface MultipleStringSearchService {

    MatchResponseBean match(String dict, String text) throws Exception;

    SearchResponseBean search(String dict, String text) throws Exception;

    String replace(String dict, String string, String replacement) throws Exception;

    String[] multiReplace(String dict, String[] texts, String replacement) throws Exception;

    MatchResponseBean[] multiMatch(String dict, String[] texts) throws Exception;

    SearchResponseBean[] multiSearch(String dict, String[] texts) throws Exception;

    Object multiDictMatch(String[] dict, String texts) throws Exception;

    Object multiDictSearch(String[] dict, String texts) throws Exception;
}
