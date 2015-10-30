package com.anjuke.incubator.mss.controller.storage;

import static com.anjuke.incubator.mss.common.Const.ENCODE_UTF8;
import static com.anjuke.incubator.mss.common.Utils.lineToMap;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.anjuke.incubator.mss.common.Const;
import com.anjuke.incubator.mss.service.algorithm.Algorithm;
import com.anjuke.incubator.mss.service.storage.RedisService;
import com.google.common.collect.ImmutableMap;

@Controller
public class WordUploadController {

    private static final Logger _logger =
        LoggerFactory.getLogger(WordUploadController.class);

    @Resource
    RedisService redis;

    @Resource
    Algorithm algorithm;

    @RequestMapping(value = "/word/upload",
                    headers = "content-type=multipart/*",
                    produces = "application/json",
                    method = RequestMethod.POST)
    @ResponseBody
    public Object upload(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "append", required = false, defaultValue = "true") boolean append)
            throws IOException {

        _logger.info("Upload file {}", file.getOriginalFilename());

        if(!file.isEmpty()){
            readFileToRedis(dict, file, append);
        } else {
            _logger.warn("File is empty");
        }

        if(!algorithm.exists(dict)){
            algorithm.create(dict);
        }

        return ImmutableMap.<String, String> of(
                Const.STATUS, Const.OK,
                Const.MESSAGE, Const.SUCCESS_IMPORT);
    }

    @RequestMapping(value = "/word/upload",
                    produces = "application/json",
                    method = RequestMethod.GET)
    @ResponseBody
    public Object upload(
            @RequestParam(value = "dicname", required = true) String dict,
            @RequestParam(value = "keyword", required = true) String keyword,
            @RequestParam(value = "type", required = false, defaultValue = "") String type)
            throws IOException {

        redis.setKeyValue(dict,
                ImmutableMap.<String, String> of(keyword, type));

        return ImmutableMap.<String, String> of(
                Const.STATUS, Const.OK,
                Const.MESSAGE, Const.SUCCESS_IMPORT);
    }

    public void readFileToRedis(String dict, MultipartFile file, boolean append) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(file.getInputStream(), writer, ENCODE_UTF8);
        } catch (IOException e) {
            _logger.error(e.getMessage());
        }

        Map<String, String> map = new HashMap<String, String>();
        String[] lines = writer.toString().split("\\n");
        for(String line: lines) {
            map.putAll(lineToMap(line));
        }

        if(!append){
            redis.delete(dict);
        }
        redis.setKeyValue(dict, map);
    }
}
