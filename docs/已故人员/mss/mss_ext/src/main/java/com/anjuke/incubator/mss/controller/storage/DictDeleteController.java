package com.anjuke.incubator.mss.controller.storage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.exception.DictNotFoundException;
import com.anjuke.incubator.mss.service.algorithm.Algorithm;
import com.anjuke.incubator.mss.service.storage.RedisService;
import com.google.common.collect.ImmutableMap;

@Controller
public class DictDeleteController {

    @Resource
    Algorithm algorithm;

    @Resource
    RedisService redis;

    @RequestMapping(value = "/dict/delete", produces = "application/json")
    @ResponseBody
    public Object deleteDictionary(
            @RequestParam(value = "dicname", required = true) String dict)
            throws DictNotFoundException {
     if(!algorithm.exists(dict)) {
            throw new DictNotFoundException(dict);
        }
        algorithm.delete(dict);
        redis.delete(dict);

        return ImmutableMap.<String, String> of(Const.OK, Const.SUCCESS_DELETE);
    }
}
