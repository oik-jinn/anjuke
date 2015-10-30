package com.anjuke.incubator.mss.controller.mss;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.service.mss.MultipleStringSearchService;

@Controller
public class MultiOperationController {

    @Resource
    MultipleStringSearchService mssService;

    @RequestMapping(value = "/mss/multiDictMatch", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object multiDictMatch(
            @RequestParam(value = "dicname", required = true) String[] dicts,
            @RequestParam(value = "text", required = true) String text)
            throws Exception {
        return mssService.multiDictMatch(dicts, text);
    }

    @RequestMapping(value = "/mss/multiDictSearch", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object multiDictSearch(
            @RequestParam(value = "dicname", required = true) String[] dicts,
            @RequestParam(value = "text", required = true) String text)
            throws Exception {
        return mssService.multiDictSearch(dicts, text);
    }

    @RequestMapping(value = "/mss/multiMatch", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object multiMatch(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "text", required = true) String[] texts)
            throws Exception {
        return mssService.multiMatch(dict, texts);
    }

    @RequestMapping(value = "/mss/multiSearch", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object multiSearch(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "text", required = true) String[] texts)
            throws Exception {
        return mssService.multiSearch(dict, texts);
    }
}
