package com.anjuke.incubator.mss.service.mss.impl;

import javax.annotation.Resource;

import com.anjuke.incubator.mss.bean.MatchResponseBean;
import com.anjuke.incubator.mss.bean.SearchResponseBean;
import com.anjuke.incubator.mss.service.mss.MultipleStringSearchApsService;
import com.anjuke.incubator.mss.service.mss.MultipleStringSearchService;

public class MultipleStringSearchApsServiceImpl implements
        MultipleStringSearchApsService {

    @Resource
    MultipleStringSearchService mssService;

    public MatchResponseBean match(String dict, String text) throws Exception {

        return mssService.match(dict, text);
    }

    public SearchResponseBean search(String dict, String text) throws Exception {

        return mssService.search(dict, text);
    }

    public MatchResponseBean[] multiMatch(String dict, String[] texts)
            throws Exception {

        return mssService.multiMatch(dict, texts);
    }

    public SearchResponseBean[] multiSearch(String dict, String[] texts)
            throws Exception {

        return mssService.multiSearch(dict, texts);
    }

    public String replace(String dict, String text, String replacement)
            throws Exception {

        return mssService.replace(dict, text, replacement);
    }

    public String[] multiReplace(String dict, String[] texts, String replacement)
            throws Exception {

        return mssService.multiReplace(dict, texts, replacement);
    }

    public Object multiDictMatch(String[] dicts, String text) throws Exception {

        return mssService.multiDictMatch(dicts, text);
    }

    public Object multiDictSearch(String[] dicts, String text) throws Exception {

        return mssService.multiDictMatch(dicts, text);
    }
}
