package com.aisino.netfix.ribbon;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws ExecutionException {


        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        Objects.equal(null,null);
        java.util.Objects.equals(null,null);
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(30L, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());

        System.err.println(cache.size());

        cache.put("name","zhangSan");


        System.err.println(cache.size());
        System.err.println(String.valueOf(cache.get("name")));
        System.err.println(String.valueOf(cache.get("name")));

        Object testNull = cache.get("testNull");

        System.err.println("over");


    }


    public static CacheLoader<String, Object> createCacheLoader() {
        return new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                System.err.println("createCacheLoader.... [key = "+key+"]");
                return new Object();
            }
        };
    }


    public void test(){
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(); // look Ma, no CacheLoader
        try {
            // If the key wasn't in the "easy to compute" group, we need to
            // do things the hard way.
            cache.get("key", new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

}
