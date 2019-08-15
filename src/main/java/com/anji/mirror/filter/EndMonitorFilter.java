package com.anji.mirror.filter;

import com.anji.mirror.model.MirrorApiModel;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter;
import org.springframework.util.ReflectionUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Title: API RT QPS日志记录过滤器
 */
public class EndMonitorFilter extends SendResponseFilter {

    private static Logger logger = LoggerFactory.getLogger(EndMonitorFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 999;
    }
    private static  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public boolean shouldFilter() {
        return !RequestContext.getCurrentContext().getZuulResponseHeaders().isEmpty() || RequestContext.getCurrentContext().getResponseDataStream() != null
                || RequestContext.getCurrentContext().getResponseBody() != null;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            // 魔镜日志监控处理
            MirrorApiModel mirrorApiModel = (MirrorApiModel) context.get("mirrorLogModel");
            if(mirrorApiModel==null){
                return null;
            }
            int status=context.getResponseStatusCode();
            mirrorApiModel.setResponseStatusCode(status);
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            mirrorApiModel.setResponseTimestamp(nowTime.getTime());
            mirrorApiModel.setResponseDatetime(df.format(nowTime));
            mirrorApiModel.setResponseTime(mirrorApiModel.getResponseTimestamp() - mirrorApiModel.getRequestTimestamp());
            logger.info(mirrorApiModel.toString());

        } catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }
}
