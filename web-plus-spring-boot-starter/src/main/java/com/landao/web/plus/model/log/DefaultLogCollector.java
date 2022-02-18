package com.landao.web.plus.model.log;

import com.landao.web.plus.core.RequestLog;
import com.landao.web.plus.core.RequestLogCollector;
import com.landao.web.plus.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLogCollector implements RequestLogCollector {

    private static final Logger log = LoggerFactory.getLogger(DefaultLogCollector.class);

    @Override
    public void collectRequestLog(RequestLog requestLog) {
        log.info(JsonUtils.parse(requestLog));
    }

}
