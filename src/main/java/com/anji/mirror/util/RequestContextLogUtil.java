package com.anji.mirror.util;

import com.anji.mirror.model.MirrorApiModel;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 请求上下文工具类
 */
public class RequestContextLogUtil {

    public static  DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**设置mirror zuul监控对象
     * @param requestContext
     * @param request
     */
    public static void setMirrorApiRequestTime(RequestContext requestContext, HttpServletRequest request){
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String requestUrl=request.getRequestURL().toString();
        String apiName=requestUrl.substring(requestUrl.indexOf("/", requestUrl.indexOf("://") + 3));

        // 设置魔镜日志监控对象
        MirrorApiModel mirrorApiModel = new MirrorApiModel();
        mirrorApiModel.setApiName(apiName);
        mirrorApiModel.setRequestTimestamp(nowTime.getTime());
        mirrorApiModel.setRequestDatetime(df.format(nowTime));
        requestContext.set("mirrorLogModel", mirrorApiModel);
    }
}
