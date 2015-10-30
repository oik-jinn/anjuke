package com.anjuke.incubator.mss.exception;

@SuppressWarnings("serial")
public class DuplicatedDictException extends MssBaseException {

    private static final String errMsg = "Dictionary already exists : ";

    public DuplicatedDictException(String dict) {
        super(errMsg + dict);
    }
}
