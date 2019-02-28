package com.alex.leaves.controller;

import com.alex.leaves.service.IdGeneratorService;
import com.alex.leaves.service.IdInfoService;
import com.alex.leaves.entity.IdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    IdInfoService service;

    @Autowired
    IdGeneratorService idGeneratorService;

    @RequestMapping("/user")
    public IdInfo get() {
        String name = "user";
        return service.getByName(name);
    }

    @RequestMapping("/update")
    public Integer update() {
        String name = "user";
        return service.updateMax(name);
    }

    @RequestMapping("/correct")
    public String nums() {
        String name = "user";
        ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
        Set<Long> s = Collections.synchronizedSet(new HashSet<Long>());
        new Thread(()->{
            int i = 2000;
            while (i-- > 0) {
                Long id = idGeneratorService.nextId(name);
                System.out.println(id);
                queue.add(id);
                s.add(id);
            }
        }).start();
        new Thread(()->{
            int i = 2000;

            while (i-- > 0) {
                Long id = idGeneratorService.nextId(name);
                System.out.println(id);
                queue.add(id);
                s.add(id);
            }
        }).start();
        return s.size() + "  " + queue.size();
    }

    @RequestMapping("/qps")
    public Long qps() {
        Long count = 0L;
        String name = "user";
        Long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < 1000) {
            idGeneratorService.nextId(name);
            count++;
        }
        return count;
    }
}
