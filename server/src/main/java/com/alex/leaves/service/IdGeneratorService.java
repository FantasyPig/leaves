package com.alex.leaves.service;

import com.alex.leaves.entity.IdGenerator;
import com.alex.leaves.factory.IdGeneratorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {

    @Autowired
    IdGeneratorFactory idGeneratorFactory;

    public Long nextId(String name) {
        IdGenerator idGenerator = idGeneratorFactory.getIdGenerator(name);
        return idGenerator.nextId();
    }
}
