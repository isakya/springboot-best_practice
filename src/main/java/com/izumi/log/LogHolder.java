package com.izumi.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogHolder {
    public static ThreadLocal<LogParam> LOG_THEAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前请求日志信息
     *
     * @param vo
     */
    public static void set(LogParam vo) {
        LOG_THEAD_LOCAL.set(vo);
    }

    /**
     * 获取当前请求日志信息
     *
     * @return
     */
    public static LogParam get() {
        return LOG_THEAD_LOCAL.get();
    }

    /**
     * 删除当前请求日志信息
     */
    public static void remove() {
        LOG_THEAD_LOCAL.remove();
    }

    public static void writeLog() {
        LogParam param = LOG_THEAD_LOCAL.get();
        if (param == null) return;
        log.debug("request-no:{},userId:{},userName:{},url:{},ip:{},brower:{},time:{},params:{},resData:{}",
                param.getRequestNo(),
                param.getUserId(),
                param.getUserName(),
                param.getUrl(),
                param.getIp(),
                param.getBrower(),
                param.getTime(),
                param.getParams(),
                param.getBody()
        );
    }
}
