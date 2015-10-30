package com.anjuke.incubator.mss.controller.mss;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.service.mss.MultipleStringSearchService;

@Controller
public class ReplaceOperationController {

    @Resource
    MultipleStringSearchService mssService;

    @RequestMapping(value = "/mss/replace", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object replace(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "text", required = true) String text,
            @RequestParam(value = "replacement", required = true) String replacement)
            throws Exception {
        return mssService.replace(dict, text, replacement);
    }

    @RequestMapping(value = "/mss/multiReplace", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object multiReplace(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "text", required = true) String[] texts,
            @RequestParam(value = "replacement", required = true) String replacement)
            throws Exception {
        return mssService.multiReplace(dict, texts, replacement);
    }
}
