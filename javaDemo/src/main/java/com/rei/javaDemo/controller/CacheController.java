package com.rei.javaDemo.controller;

import com.google.common.cache.Cache;
import com.google.common.cache.LoadingCache;
import com.rei.javaDemo.example.CacheExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("cache")
public class CacheController {

    @Autowired
    private CacheExample cacheExample;

    @RequestMapping("getCache")
    public void getCache() throws ExecutionException {
        LoadingCache<String,Object> c = cacheExample.getCatch();
        Object str = c.get("test");
        int a =0;
    }
}
