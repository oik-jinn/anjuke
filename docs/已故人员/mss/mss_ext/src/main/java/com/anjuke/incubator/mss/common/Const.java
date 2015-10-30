package com.anjuke.incubator.mss.common;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public abstract class Const {

    public static String ID = "id";
    public static String TYPE = "type";
    public static String SOURCE = "source";

    public static String BAN_DICT_NAME = "banwords-dict";
    public static String BAN_DICT_ID = "11";
    public static String BAN_DICT_TYPE = "ban";
    public static String BAN_DICT_SOURCE = "1";

    public static String WENDA_DICT_NAME = "wenda-dict";
    public static String WENDA_DICT_ID = "21";
    public static String WENDA_DICT_TYPE = "wenda";
    public static String WENDA_DICT_SOURCE = "2";

    public static String WENDA_PROMPT_DICT_NAME = "wenda-prompt-dict";
    public static String WENDA_PROMPT_DICT_ID = "22";
    public static String WENDA_PROMPT_DICT_TYPE = "wenda-prompt";
    public static String WENDA_PROMPT_DICT_SOURCE = "2";

    public static String STATUS = "status";
    public static String MESSAGE = "message";

    public static String SUCCESS_CREATE = "created successful";
    public static String SUCCESS_RELOAD = "reload successful";
    public static String SUCCESS_IMPORT = "import successful";
    public static String SUCCESS_DELETE = "delete successful";

    public static String DUPLICATED_DIC = "dumplicate dictionary name";
    public static String LACK_DIC = "in short of dictionary name";
    public static String LACK_KEYWORD = "in short of keyword";
    public static String LACK_FILE = "in short of file";
    public static String NONE_EXSITS_DATA = "the data is not exist";
    public static String NONE_EXSITS_DIC = "the dictionary is not exist";
    public static String FILE_EMPTY = "the file is empty";

    public static final String OK = "ok";
    public static final String FAIL = "fail";

    public static final Boolean MATCH_TRUE = true;
    public static final Boolean MATCH_FALSE = false;

    public static final String MATCH_TRUE_STRING = "true";
    public static final String MATCH_FALSE_STRING = "false";

    public static final String ELEMENT_SET = "set";

    public static final String ENCODE_UTF8 = "UTF-8";

    public static final String REDIS_LIBRARY = "redislib";
    public static final String REDIS_IP = "redisip";
    public static final String REDIS_PORT = "redisport";

    public static final String BUSINESS_NAME = "businessname";

    public static final String TYPE_DOWNLOAD = "download";

    public static final String POOL_ONE = "1";
    public static final String POOL_TWO = "2";

    public static final String SPLIT_UP_BY_TAB = "\\s";

    public static final Map<String, String> EmptyMap =
        ImmutableMap.<String, String> of("created", "");

    /**
     * This ugly map is just:
     *
     * infosec = {
     *     "banwords-dict": {
     *         "id": 1
     *         "type": "ban"
     *         "source": 11
     *     },
     *     "wenda-dict": {
     *         "id": 2
     *         "type": "wenda"
     *         "source": 21
     *     },
     *     "wenda-prompt-dict": {
     *         "id": 2
     *         "type": "wenda-prompt"
     *         "source": 22
     *     }
     * }
     */
    public static Map<String, Map<String, String>> infoSecMap =
        ImmutableMap.<String, Map<String, String>> of(
                BAN_DICT_NAME, ImmutableMap.<String, String> of(
                    ID, BAN_DICT_ID,
                    TYPE, BAN_DICT_TYPE,
                    SOURCE, BAN_DICT_SOURCE),
                WENDA_DICT_NAME, ImmutableMap.<String, String> of(
                    ID, WENDA_DICT_ID,
                    TYPE, WENDA_DICT_TYPE,
                    SOURCE, WENDA_DICT_SOURCE),
                WENDA_PROMPT_DICT_NAME, ImmutableMap.<String, String> of(
                    ID, WENDA_PROMPT_DICT_ID,
                    TYPE, WENDA_PROMPT_DICT_TYPE,
                    SOURCE, WENDA_PROMPT_DICT_SOURCE));

}
