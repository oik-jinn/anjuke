package com.anjuke.incubator.mss.exception;

import com.anjuke.aps.ApsStatus;

@SuppressWarnings("serial")
public class MssBaseException extends Exception {

    private int status = ApsStatus.BAD_REQUEST;

    public MssBaseException(String msg) {
        super(msg);
    }

    public MssBaseException(String msg, int status) {
        super(msg);
        setStatus(status);
    }

    public void setStatus(int status) {
        this.status =  status;
    }

    public int getStatus() {
        return status;
    }

}
