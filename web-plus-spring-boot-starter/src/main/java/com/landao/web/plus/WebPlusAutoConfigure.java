package com.landao.web.plus;


import com.landao.web.plus.annotation.RequestController;
import com.landao.web.plus.core.LogCollectorAdvice;
import com.landao.web.plus.core.RequestLogAspect;
import com.landao.web.plus.core.RequestLogCollector;
import com.landao.web.plus.model.log.CommonResultLog;
import com.landao.web.plus.core.RequestLog;
import com.landao.web.plus.model.log.DefaultLogCollector;
import com.landao.web.plus.utils.JsonUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(WebPlusProperties.class)
@Import({RequestLogAspect.class, LogCollectorAdvice.class})
public class WebPlusAutoConfigure {

    @Bean
    public JsonUtils jsonUtils(){
        return new JsonUtils();
    }

    @Bean
    public WebPlusManager webPlusManager(){
        return new WebPlusManager();
    }

    @Bean
    @Scope(value = "prototype")
    @ConditionalOnMissingBean(RequestLog.class)
    public RequestLog requestLog(){
        return new CommonResultLog();
    }

    @Bean
    @ConditionalOnMissingBean(RequestLogCollector.class)
    public RequestLogCollector requestLogCollector(){
        return new DefaultLogCollector();
    }


}
