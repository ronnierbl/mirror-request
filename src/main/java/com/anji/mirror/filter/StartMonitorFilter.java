package com.anji.mirror.filter;

import com.anji.mirror.util.RequestContextLogUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

public class StartMonitorFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(StartMonitorFilter.class);
    @Bean
    public EndMonitorFilter endMonitorFilter(){
        return new EndMonitorFilter();
    }

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 99;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
//        String ipAddr = RequestUtil.getIpAddr(request);
//        String requestURL = request.getRequestURL().toString();
//        String servletPath = request.getServletPath();          // /business/accessUser/login
//        String requestMethod = request.getMethod();             // POST GET
//        String contentType = request.getContentType();          // application/x-www-form-urlencoded, multipart/form-data, application/json
//        logger.info(String.format("收到来自IP为 %s 的请求%s request to %s", ipAddr, requestMethod, requestURL));

        //设置mirror api请求开始时间
		RequestContextLogUtil.setMirrorApiRequestTime(ctx,request);
        return null;
    }

}
