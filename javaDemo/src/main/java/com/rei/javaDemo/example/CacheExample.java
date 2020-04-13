package com.rei.javaDemo.example;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存工具类
 */
@Service
@Slf4j
public class CacheExample {

    private static LoadingCache<String, Object> caches = null;
    public CacheExample(){
        // 初始化加载缓存
        loadCache();
    }
    /**
     *  初始化缓存
     *  expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
     *  expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。
     *  refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。
     */
    public void loadCache() {
        caches = CacheBuilder.newBuilder()
                //  最大缓存数量，扩展成本很高，最好在创建最初搞定
                .maximumSize(100)
                // 缓存失效时间，过了这个时间会刷新缓存的
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        // key 代表缓存的key，返回值是该key对应的返回值
                        log.info("加载缓存中");
                        return generateValueByKey(key);
                    }
                });
    }

    /**
     * 根据key获取缓存的方法
     * @param key key
     * @return 需要放入缓存的obj
     */
    private String generateValueByKey(String key){
        log.info("加载xxx缓存，key={}",key);
        log.info("xxx缓存加载完毕，key={}",key);
        return "test9999";
    }

    public LoadingCache<String,Object>  getCatch(){
        return caches;
    }

    /**
     * 根据key获取缓存内容
     * @param key
     * @return
     */
    public Object getCatchByKey(String key){
        try {
            return caches.get(key);
        }catch (Exception e){
            log.error("获取缓存失败",e);
        }
        return null;
    }
}
