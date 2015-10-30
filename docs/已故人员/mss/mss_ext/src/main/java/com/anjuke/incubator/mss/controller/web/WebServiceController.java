package com.anjuke.incubator.mss.controller.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebServiceController {

    @RequestMapping(value = "/")
    public String index() throws IOException {
        return "index";
    }

    @RequestMapping(value = "/mss/web")
    public String multipleStringMatch() throws IOException {
        return "index";
    }
}
