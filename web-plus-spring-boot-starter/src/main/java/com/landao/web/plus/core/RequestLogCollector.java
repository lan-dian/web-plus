package com.landao.web.plus.core;

import org.springframework.scheduling.annotation.Async;

public interface RequestLogCollector {

    @Async
    void collectRequestLog(RequestLog requestLog);

}
