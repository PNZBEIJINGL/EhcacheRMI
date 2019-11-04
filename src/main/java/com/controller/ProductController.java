package com.controller;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

    @GetMapping("/save")
    public String saveProduct() {
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("daoCache");
        // 3. 创建元素
        for (int i = 0; i < 5; i++) {
            Element element = new Element(i, "product " + i);
            // 4. 将元素添加到缓存
            cache.put(element);
        }

        return "success";

    }

    @GetMapping("/update")
    public String updateProduct() {
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("daoCache");
        Element element = new Element(1, "update product " + 1);
        cache.put(element);
        return "success";
    }

    @GetMapping("/get")
    public String getProduct() {
        CacheManager cacheManager = CacheManager.create("./src/main/resources/ehcache.xml");
        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("daoCache");
        Element e = cache.get(1);
        return e.getObjectValue().toString();
    }

    @RequestMapping("/")
    public String index() {
        return "welcome product";
    }
}
