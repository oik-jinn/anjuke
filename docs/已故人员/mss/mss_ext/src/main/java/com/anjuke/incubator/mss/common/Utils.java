package com.anjuke.incubator.mss.common;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public abstract class Utils {

    /**
     * Split plain string content into lines
     * And put line:type into HashMap
     *
     * @param content
     * @param level
     */
    public static Map<String, String> plainToMap(String content, String type){
        if(content == null) {
            return null;
        }
        String[] keywords = content.split("\\n");
        Map<String, String> map = new HashMap<String, String>();
        for(String line : keywords) {
            map.put(line, type);
        }
        return  map;
    }

    /**
     * If line is keyword:type, split by ':'
     * Else type is ""
     *
     * Put keyword:type into HashMap
     *
     * @param line
     */
    public static Map<String, String> lineToMap(String line) {
        ImmutableMap<String, String> map;
        if (line.contains(":")) {
            String[] kvList = line.split(":");
            map = ImmutableMap.<String, String> of(
                        kvList[0],
                        kvList.length == 2 ? kvList[1] : "");
        } else {
            map = ImmutableMap.<String, String> of(line, "");
        }
        return map;
    }
}
