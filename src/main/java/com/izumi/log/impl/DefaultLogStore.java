package com.izumi.log.impl;

import com.izumi.log.ILogStore;
import com.izumi.log.LogParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultLogStore implements ILogStore {

    @Override
    public void save(LogParam param) {
        // [requestNo][userId][userName][url][ip][brower][time][body][params][resData]
        log.debug("[{}][{}][{}][{}][{}][{}][{}][{}][{}][{}]",
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
