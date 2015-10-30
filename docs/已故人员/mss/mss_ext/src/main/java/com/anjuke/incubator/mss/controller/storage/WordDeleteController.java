package com.anjuke.incubator.mss.controller.storage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.exception.DictNotFoundException;
import com.anjuke.incubator.mss.exception.KeywordNotFoundException;
import com.anjuke.incubator.mss.service.storage.RedisService;
import com.google.common.collect.ImmutableMap;

@Controller
public class WordDeleteController {

    @Resource
    RedisService redis;

    @RequestMapping(value = "/word/delete", produces = "application/json")
    @ResponseBody
    public Object getDelEachDicInforms(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "keyword", required = true) String keyword)
            throws Exception {
        if (!redis.exists(dict)) {
            throw new DictNotFoundException(dict);
        }
        if (!redis.keyExists(dict, keyword)) {
            throw new KeywordNotFoundException(dict, keyword);
        }

        redis.delKeyValue(dict, keyword);
        return ImmutableMap.<String, String> of(Const.OK, Const.SUCCESS_DELETE);
    }
}
