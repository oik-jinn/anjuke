package com.anjuke.incubator.mss.exception;

@SuppressWarnings("serial")
public class DictNotFoundException extends MssBaseException {

    private static final String errMsg = "Dictionary not found : ";

    public DictNotFoundException(String dict) {
        super(errMsg + dict);
    }
}
