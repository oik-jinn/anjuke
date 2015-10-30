package com.anjuke.incubator.mss.controller.storage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anjuke.incubator.mss.service.algorithm.Algorithm;

@Controller
public class DictIndexController {

    @Resource
    Algorithm algorithm;

    @RequestMapping(value = "/dict", produces = "application/json")
    @ResponseBody
    public Object index(){
        return algorithm.getDicts();
    }
}
