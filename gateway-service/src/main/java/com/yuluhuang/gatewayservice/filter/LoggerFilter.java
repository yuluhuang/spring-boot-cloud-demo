/**
 * @Title
 * @Project spring-boot-cloud-demo
 * @Package com.yuluhuang.gatewayservice.filter
 * @Description
 * @author yoshikouamari
 * @date 2019-05-06 18:49
 * @version
 */
package com.yuluhuang.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 *
 * @Description
 * @author yoshikouamari
 * @date 2019-05-06 18:49
 */
@Component
public class LoggerFilter extends ZuulFilter {
//    @Autowired
//    Tracer tracer;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
//        tracer.addTag();
//        System.out.println(tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
