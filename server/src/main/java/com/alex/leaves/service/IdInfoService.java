package com.alex.leaves.service;

import com.alex.leaves.dao.IdInfoDao;
import com.alex.leaves.entity.IdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdInfoService {

    @Autowired
    IdInfoDao dao;

    public IdInfo getByName(String name) {
        return dao.getByName(name);
    }

    public Integer updateMax(String name) {
        return dao.updateMax(name);
    }
}
