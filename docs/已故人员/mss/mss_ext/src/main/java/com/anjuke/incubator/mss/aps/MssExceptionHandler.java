package com.anjuke.incubator.mss.aps;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anjuke.aps.ApsStatus;
import com.anjuke.aps.Request;
import com.anjuke.aps.Response;
import com.anjuke.aps.server.spring.DefaultExceptionHandler;
import com.anjuke.incubator.mss.exception.MssBaseException;

/**
 * 全局的 APS 异常处理，设置 APS Reply 的 Status，并把异常信息放在 Reply Body
 *
 */
public class MssExceptionHandler extends DefaultExceptionHandler {

    private static final Logger LOG = LoggerFactory
        .getLogger(MssExceptionHandler.class);

    @Override
    public void handleException(Exception e, Request request, Response response) {
        LOG.error(e.getMessage());

        response.setErrorMessage(e.getMessage());

        // 如果是从 MssBaseException 继承而来，根据异常的 Status 设置 Status
        // 其他异常返回 5xx
        if(e instanceof MssBaseException){
            response.setStatus(((MssBaseException) e).getStatus());
        } else {
            response.setStatus(ApsStatus.INTENAL_SERVER_ERROR);
        }
    }
}
