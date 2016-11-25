package com.dangdang.json;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SingleObjectProfile {

    private static final Logger logger = LoggerFactory.getLogger(SingleObjectProfile.class);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Profiler profile = new Profiler("json libary");

            jackson(profile);
            fastjson(profile);
            gson(profile);

            jacksonList(profile);
            fastjsonList(profile);
            gsonList(profile);

            reverseGsonJson(profile);
            reverseFastJson(profile);
            reverseJacksonJson(profile);

            logger.info("{}", profile.stop());
        }

    }

    //{"age":120,"a":234,"b":12,"c":"新","f":2.3,"d":3.5,"now":"2016-11-18 14:28:46","address":{"zipCode":"100028","location":"北京朝阳区"},"person_name":"ghost"}
    private static Person reverseJacksonJson(Profiler profile) {
        profile.start("reverseJacksonJson");
        try {
            String json = "{\"age\":120,\"a\":234,\"b\":12,\"c\":\"新\",\"f\":2.3,\"d\":3.5,\"now\":\"2016-11-18 14:28:46\",\"address\":{\"zipCode\":\"100028\",\"location\":\"北京朝阳区\"},\"person_name\":\"ghost\"}";
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Person.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void jackson(Profiler profile) {
        try {
            profile.start("jackson");
            ObjectMapper mapper = new ObjectMapper();
            //pretty print
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Person p = getSinglePerson();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p);
            logger.info("jackson:{}", json);
        } catch (JsonProcessingException e) {
            logger.error("jackson error:", e);
        }
    }

    private static void jacksonList(Profiler profile) {
        try {
            profile.start("jackson list");
            ObjectMapper mapper = new ObjectMapper();

            List<Person> persons = getPersonList();

            String json = mapper.writeValueAsString(persons);
            logger.info("jackson list:{}", json);
        } catch (JsonProcessingException e) {
            logger.error("", e);
        }
    }

    private static void fastjsonList(Profiler profile) {
        profile.start("fastjson list");
        List<Person> persons = getPersonList();
        String json = JSON.toJSONString(persons);
        logger.info("fastjson list:{}", json);
    }


    private static List<Person> getPersonList() {
        Person p = new Person("'\"ghost", 120);
        Person p1 = new Person("!@$", 120);
        Person p2 = new Person("^&*((", 120);

        p.setA(234L);
        p.setB((byte) 12);
        p.setC('新');
        p.setD(3.5D);
        p.setF(2.3F);
        p.setNow(new Date());
        Address address = new Address("100028", "北京朝阳区");
        p.setAddress(address);

        p1.setA(234L);
        p1.setB((byte) 12);
        p1.setC('新');
        p1.setD(3.5D);
        p1.setF(2.3F);
        p1.setNow(new Date());
        Address address1 = new Address("100028", "北京东城区");
        p1.setAddress(address1);

        p2.setA(234L);
        p2.setB((byte) 12);
        p2.setC('新');
        p2.setD(3.5D);
        p2.setF(2.3F);
        p2.setNow(new Date());
        Address address2 = new Address("100028", "北京海淀区");
        p2.setAddress(address2);

        List<Person> persons = Lists.newArrayList(p, p1, p2);
        return persons;
    }

    //{"a":234,"address":{"location":"北京朝阳区","zipCode":"100028"},"age":120,"all_name":"ghost","b":12,"c":"新","d":3.5,"f":2.3,"now":"2016-11-18 14:28:46"}
    private static Person reverseFastJson(Profiler profile) {
        profile.start("fastjson reverse");
        String json = "{\"a\":234,\"address\":{\"location\":\"北京朝阳区\",\"zipCode\":\"100028\"},\"age\":120,\"all_name\":\"ghost\",\"b\":12,\"c\":\"新\",\"d\":3.5,\"f\":2.3,\"now\":\"2016-11-18 14:28:46\"}";
        return JSON.parseObject(json, Person.class);
    }

    private static void fastjson(Profiler profile) {
        profile.start("fastjson");
        Person p = getSinglePerson();
        //pretty print
        String json = JSON.toJSONString(p, true);
        logger.info("fastjson:{}", json);
    }

    private static Person getSinglePerson() {
        Person p = new Person("ghost", 120);
        p.setA(234L);
        p.setB((byte) 12);
        p.setC('新');
        p.setD(3.5D);
        p.setF(2.3F);
        p.setNow(new Date());

        Address address = new Address("100028", "北京朝阳区");
        p.setAddress(address);
        return p;
    }

    //{"full_name":"ghost","age":120,"a":234,"b":12,"c":"新","f":2.3,"d":3.5,"now":"2016-11-18 14:39:31","address":{"zipCode":"100028","location":"北京朝阳区"}}
    private static Person reverseGsonJson(Profiler profile) {
        profile.start("gson reverse");
        Gson gson = new GsonBuilder()
                //pretty print
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        String json = "{\"a\":234,\"address\":{\"location\":\"北京朝阳区\",\"zipCode\":\"100028\"},\"age\":120,\"all_name\":\"ghost\",\"b\":12,\"c\":\"新\",\"d\":3.5,\"f\":2.3,\"now\":\"2016-11-18 14:28:46\"}";
        return gson.fromJson(json, Person.class);
    }

    private static void gson(Profiler profile) {
        profile.start("gson");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Person p = getSinglePerson();
        String json = gson.toJson(p);
        logger.info("gson:{}", json);
    }

    private static void gsonList(Profiler profile) {
        profile.start("gson list");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        List<Person> persons = getPersonList();
        String json = gson.toJson(persons);
        logger.info("gson list:{}", json);
    }
}
