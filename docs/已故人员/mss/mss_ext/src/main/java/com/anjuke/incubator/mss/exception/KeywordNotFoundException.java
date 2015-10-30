package com.anjuke.incubator.mss.exception;

@SuppressWarnings("serial")
public class KeywordNotFoundException extends MssBaseException {

    private static final String Keyword = "Keyword: ";
    private static final String NotFound = " not found in dictionary: ";

    public KeywordNotFoundException(String dict, String keyword) {
        super(Keyword + keyword + NotFound + dict);
    }
}
