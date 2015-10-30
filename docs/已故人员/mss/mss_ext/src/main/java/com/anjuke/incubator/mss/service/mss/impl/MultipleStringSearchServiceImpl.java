package com.anjuke.incubator.mss.service.mss.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.anjuke.incubator.mss.bean.DetailsBean;
import com.anjuke.incubator.mss.bean.MatchResponseBean;
import com.anjuke.incubator.mss.bean.SearchResponseBean;
import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.service.algorithm.Algorithm;
import com.anjuke.incubator.mss.service.mss.MultipleStringSearchService;
import com.anjuke.incubator.mss.service.storage.RedisService;

import static com.google.common.base.Preconditions.*;

@Service
public class MultipleStringSearchServiceImpl implements MultipleStringSearchService {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory
            .getLogger(MultipleStringSearchServiceImpl.class);

    @Resource
    RedisService redis;

    @Resource
    Algorithm algorithm;

    @Override
    public MatchResponseBean match(String dict, String text)
            throws Exception {
        Trie trie = algorithm.getTrie(dict);

        MatchResponseBean result = new MatchResponseBean();
        result.setDicname(dict);
        result.setStatus(Const.OK);

        if( text == null){
            result.setMatch(Const.MATCH_FALSE_STRING);
        } else {
            List<Emit> emits = (List<Emit>) trie.parseText(text);

            result.setMatch(emits.size() > 0 ? Const.MATCH_TRUE_STRING
                    : Const.MATCH_FALSE_STRING);
            if(emits.size() > 0){
                result.setDetails(emits);
            }
        }

        return result;
    }

    @Override
    public SearchResponseBean search(String dict, String text)
            throws Exception {
        Trie trie = algorithm.getTrie(dict);

        SearchResponseBean result = new SearchResponseBean();
        result.setDicname(dict);
        result.setStatus(Const.OK);

        if( text == null){
            result.setMatch(Const.MATCH_FALSE_STRING);
        } else {
            List<Emit> emits = (List<Emit>) trie.parseText(text);

            result.setMatch(emits.size() > 0 ? Const.MATCH_TRUE_STRING
                    : Const.MATCH_FALSE_STRING);
            if(emits.size() > 0){
                result.setDetails(getDetails(dict, emits));
            }
        }

        return result;
    }

    @Override
    public String replace(String dict, String text, String replacement)
            throws Exception {
        if(replacement == null){
            replacement = "";
        }
        Trie trie = algorithm.getTrie(dict);

        if( text == null){
            return "";
        } else {
            List<Emit> emits = (List<Emit>) trie.parseText(text);
            if(emits.size() == 0) {
                return text;
            } else {
                HashSet<String> kwSet = new HashSet<String>();
                for(Emit emit: emits) {
                    kwSet.add(emit.getKeyword());
                }
                for(String kw: kwSet) {
                    text = text.replaceAll(kw, replacement);
                }
                return text;
            }
        }
    }

    @Override
    public String[] multiReplace(String dict, String[] texts, String replacement)
            throws Exception {
        String[] result = new String[texts.length];
        for(int i = 0; i < texts.length; i++) {
            result[i] = replace(dict, texts[i], replacement);
        }
        return result;
    }

    @Override
    public MatchResponseBean[] multiMatch(String dict, String[] texts)
            throws Exception {
        checkNotNull(texts, "String[] must not be null");

        MatchResponseBean[] result = new MatchResponseBean[texts.length];
        for(int i = 0; i < texts.length; i++) {
            result[i] = match(dict, texts[i]);
        }
        return result;
    }

    @Override
    public SearchResponseBean[] multiSearch(String dict, String[] texts)
            throws Exception {
        checkNotNull(texts, "String[] must not be null");

        SearchResponseBean[] result = new SearchResponseBean[texts.length];
        for(int i = 0; i < texts.length; i++) {
            result[i] = search(dict, texts[i]);
        }
        return result;
    }

    @Override
    public Object multiDictMatch(String[] dicts, String text) {
        List<MatchResponseBean> result = new ArrayList<MatchResponseBean>();
        for(int i = 0; i < dicts.length; i++) {
            try {
                result.add(match(dicts[i], text));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public Object multiDictSearch(String[] dicts, String text) {
        List<SearchResponseBean> result = new ArrayList<SearchResponseBean>();
        for(int i = 0; i < dicts.length; i++) {
            try {
                result.add(search(dicts[i], text));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    private List<DetailsBean> getDetails(String dict, List<Emit> emits) {
        List<DetailsBean> details = new ArrayList<DetailsBean>();

        String[] keywords = new String[emits.size()];
        for (int i = 0; i < keywords.length; i++) {
            keywords[i] = emits.get(i).getKeyword();
        }
        List<String> keyValues = redis.getMultiKeyValue(dict, keywords);

        for (int i = 0; i < keywords.length; i++) {
            DetailsBean detail = new DetailsBean(emits.get(i));
            detail.setType(keyValues.get(i) == null ? "" : keyValues.get(i));
            details.add(detail);
        }
        return details;
    }

}
