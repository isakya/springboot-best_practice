package com.izumi.log;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

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

    private static Map<String, ILogStore> logStoreMap = SpringUtil.getBeansOfType(ILogStore.class);

    /**
     * 日志输出
     */
    public static void writeLog() {
        LogParam param = LOG_THEAD_LOCAL.get();
        if (param == null) return;
        // json转为字符串
        if(JSONUtil.isTypeJSON(param.getBody())) {
            param.setBody(JSONUtil.toJsonStr(JSONUtil.parse(param.getBody())));
        }
        SpringUtil.getBeansOfType(ILogStore.class);
        logStoreMap.forEach((key, logStore) -> {
            logStore.save(param);
        });
    }
}
