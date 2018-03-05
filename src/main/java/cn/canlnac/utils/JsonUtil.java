package cn.canlnac.utils;

import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Json工具类
 */
public class JsonUtil {

    public static JavaType getSimpleType(Type type) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.constructType(type);
    }

    public static JavaType getCollectionType(Class<?> parametrized, Class<?> parametersFor,
                                             Class<?>... parameterClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametrizedType(parametrized, parametersFor, parameterClasses);
    }

    public static JavaType getCollectionType(Class<?> parametrized, Class<?> parametersFor,
                                             JavaType... parameterTypes) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametrizedType(parametrized, parametersFor, parameterTypes);
    }

    public static String toJSONString(Object obj) {
        String jsonString = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static byte[] toJSONBytes(Object obj) {
        byte[] jsonBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            jsonBytes = objectMapper.writeValueAsBytes(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonBytes;
    }

    public static <T> T toJSONObj(String jsonString, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T obj = null;
        try {
            obj = objectMapper.readValue(jsonString, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static <T> T toJSONObj(String jsonString, JavaType javaType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T obj = null;
        try {
            obj = objectMapper.readValue(jsonString, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static <T> T toJSONObj(byte[] jsonBytes, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T obj = null;
        try {
            obj = objectMapper.readValue(jsonBytes, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static <T> T toJSONObj(byte[] jsonBytes, JavaType javaType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T obj = null;
        try {
            obj = objectMapper.readValue(jsonBytes, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
