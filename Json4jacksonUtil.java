

import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang.StringUtils;

/**
 * 通用的基于jackson 工具类
 *
 * @author liyebing created on  15/9/2.
 * @version $Id$
 */
public class Json4jacksonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);

        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
        module.addSerializer(Date.class, new FDateJsonSerializer());
        module.addDeserializer(Date.class, new FDateJsonDeserializer());

        objectMapper.registerModule(module);

    }

    public static ObjectMapper getObjectMapperInstance() {
        return objectMapper;
    }

    public static String toJson(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(String json, Class<T> cls) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }

            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> T toBean(String json,TypeReference<?> valueTypeRef){
        try{
            return (T)objectMapper.readValue(json, valueTypeRef);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



}