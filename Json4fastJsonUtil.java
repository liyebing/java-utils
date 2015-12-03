import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;


/**
 * 通用的基于fastjson工具类
 *
 * @author liyebing created on 15/10/22.
 * @version $Id$
 */
public class Json4fastJsonUtil {

    public static String toJson(Object obj, String dateFormat) {
        JSON.DEFFAULT_DATE_FORMAT = dateFormat;
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
    }

    public static String toJson(Object obj) {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
    }

    public static <T> T toBean(String json, Class<T> cls) {
        return (T) JSON.parseObject(json, cls);
    }

    public static <T> T toBean(String json, TypeReference<?> valueTypeRef) {
        return (T) JSON.parseObject(json, valueTypeRef);
    }

}