package com.xitao.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.graph.Graph;
import org.junit.Test;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class Cache {

    @Test
    public void cacheTest() {

        LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(null)
                .build(
                        new CacheLoader<Key, Graph>() {
                            public Graph load(Key key) throws Exception {
                                return null;
                            }
                        });

    }

}
