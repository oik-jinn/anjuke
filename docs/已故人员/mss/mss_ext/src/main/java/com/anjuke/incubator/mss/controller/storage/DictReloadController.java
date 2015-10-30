package com.anjuke.incubator.mss.controller.storage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.exception.DictNotFoundException;
import com.anjuke.incubator.mss.service.algorithm.Algorithm;
import com.anjuke.incubator.mss.service.infosec.InfosecService;
import com.google.common.collect.ImmutableMap;

@Controller
public class DictReloadController {

    private static String DOWNLOAD = "download";

    @Resource
    Algorithm algorithm;

    @Resource
    InfosecService infosec;


    @RequestMapping(value = "/dict/reload", produces = "application/json")
    @ResponseBody
    public Object reloadDic(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "type", required = false) String type)
            throws Exception {
        if( DOWNLOAD.equalsIgnoreCase(type) ) {
            infosec.fetchBanwordsToRedis(dict);
        }

        if(!algorithm.exists(dict)) {
            throw new DictNotFoundException(dict);
        }
        algorithm.reload(dict);
        return ImmutableMap.<String, String> of(Const.OK, Const.SUCCESS_RELOAD);
    }
}
