package com.alex.leaves.factory;

import com.alex.leaves.dao.IdInfoDao;
import com.alex.leaves.entity.IdGenerator;
import com.alex.leaves.service.IdGeneratorService;
import com.alex.leaves.service.IdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class IdGeneratorFactory {

    @Autowired
    IdInfoService service;


    private static ConcurrentHashMap<String, IdGenerator> generators = new ConcurrentHashMap<>();

    public IdGenerator getIdGenerator(String name) {
        if (generators.containsKey(name)) {
            return generators.get(name);
        }
        synchronized (this) {
            if (generators.containsKey(name)) {
                return generators.get(name);
            }
            IdGenerator idGenerator = createIdGenerator(name);
            generators.put(name, idGenerator);
        }
        return generators.get(name);
    }

    //TODO
    public IdGenerator createIdGenerator(String name) {
        return new IdGenerator(name, service);
    }

}
