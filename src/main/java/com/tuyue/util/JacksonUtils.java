/**
 * hxgy Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.tuyue.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * jackson工具包
 * @author XiongYu
 * @version $Id: JacksonUtils.java, v 0.1 2017年4月27日 下午1:52:52 XiongYu Exp $
 */
public class JacksonUtils {

    private static final XmlMapper xmlMapper = new XmlMapper();

    private static ObjectMapper objectMapper = new ObjectMapper();

    //--------------------------------------XML工具---------------------------------------------
    /**
     * 对象解析出xmlString
     * @param
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     * @throws Exception
     */
    public static String objectParseXml(Object object) throws JsonGenerationException,
                                                       JsonMappingException, IOException {
        StringWriter sw = new StringWriter();
        xmlMapper.writeValue(sw, object);
        return sw.toString();
    }

    /** 
     * 输出全部属性 如果xml中存在，对象中没有，则自动忽略该属性 失败返回null 
     *  
     * @param xmlContent 
     * @param clazz 
     * @return 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static <T> T xmlToNormalObject(String xmlContent, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return xmlToObject(xmlContent, clazz);
    }

    /** 
     * 输出全部属性  
     * 如果xml中存在，对象中没有，则自动忽略该属性  
     * 失败返回null 
     * @param
     * @param clazz 
     * @return 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static <T> T xmlToNormalObject(byte[] bytes, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return xmlToObject(bytes, clazz);
    }

    /** 
     * 输出全部属性 
     *  
     * @param object 
     * @return 
     * @throws JsonProcessingException 
     */
    public synchronized static byte[] toNormalXml(Object object) throws JsonProcessingException {
        return objectToXml(Include.ALWAYS, object);
    }

    private static <T> T xmlToObject(byte[] bytes, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(bytes,
            clazz);
    }

    private static <T> T xmlToObject(String xmlContent, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .readValue(xmlContent, clazz);
    }

    private static <T> byte[] objectToXml(Include include, T object) throws JsonProcessingException {
        return xmlMapper.setSerializationInclusion(include).writerWithDefaultPrettyPrinter()
            .writeValueAsBytes(object);
    }

    //---------------------------------------------json工具------------------------------------------------

    /** 
     * 输出全部属性 
     * @param object 
     * @return 
     * @throws JsonProcessingException 
     */  
    public static String toNormalJson(Object object) throws JsonProcessingException {  
        return toJson(Include.ALWAYS, object);  
    }  

    /** 
     * 输出非空属性 
     * @param object 
     * @return 
     * @throws JsonProcessingException 
     */  
    public static String toNonNullJson(Object object) throws JsonProcessingException {  
        return toJson(Include.NON_NULL, object);  
    }  

    /** 
     * 输出非Null且非Empty(如List.isEmpty)的属性 
     * @param object 
     * @return 
     * @throws JsonProcessingException 
     */  
    public static String toNonEmptyJson(Object object) throws JsonProcessingException {  
        return toJson(Include.NON_EMPTY, object);  
    }  

    /** 
     * 输出非空属性 
     * @param
     * @return 
     * @throws JsonProcessingException 
     */  
    public static String mapToJson(Map<?, ?> map) throws JsonProcessingException {  
        return objectMapper.writeValueAsString(map);
    }  

    /** 
     * 转成Json 
     * @param include 
     * @param object 
     * @return 
     * @throws JsonProcessingException 
     */  
    private static String toJson(Include include, Object object) throws JsonProcessingException {  
            objectMapper.setSerializationInclusion(include);  
            return objectMapper.writeValueAsString(object);  
    }  

    /** 
     * 输出全部属性 
     * 如果json中存在，对象中没有，则自动忽略该属性 
     * 失败返回null 
     *  
     * @param json 
     * @param clazz 
     * @return 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */  
    public static <T> T jsonToNormalObject(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {  
        return toObject(json, clazz);  
    }  

    /** 
     * 转成Object 
     * @param
     * @param json 
     * @param clazz 
     * @return 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */  
    private static <T> T toObject(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {  
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
            return objectMapper.readValue(json, clazz);  
    }
}