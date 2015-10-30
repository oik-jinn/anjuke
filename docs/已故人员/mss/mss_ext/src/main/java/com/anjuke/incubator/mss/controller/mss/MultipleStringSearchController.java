package com.anjuke.incubator.mss.controller.mss;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.bean.SearchResponseBean;
import com.anjuke.incubator.mss.service.mss.MultipleStringSearchService;

@Controller
public class MultipleStringSearchController {

    @Resource
    MultipleStringSearchService mssService;

    @RequestMapping(value = "/mss/search", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SearchResponseBean search(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "text", required = true) String text)
            throws Exception {
        return mssService.search(dict, text);
    }
}
