package com.alex.leaves.entity;

import com.alex.leaves.service.IdInfoService;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private volatile String name;

    private volatile AtomicLong currentId;

    private volatile Long step;

    private volatile Long maxId;

    private volatile IdInfoService idInfoService;

    public IdGenerator(String name, IdInfoService idInfoService) {
        IdInfo idInfo = idInfoService.getByName(name);
        this.name = idInfo.getName();
        this.currentId = new AtomicLong(idInfo.getMax());
        this.step = idInfo.getStep();
        this.maxId = idInfo.getMax() + idInfo.getDelta();
        this.idInfoService = idInfoService;
        idInfoService.updateMax(name);
    }


    public Long nextId() {
        if (currentId.get() >= maxId - step) {
            synchronized (this) {
                if (currentId.get() >= maxId - step) {
                    refresh();
                }
            }

        }
        return currentId.addAndGet(step);
    }

    private void refresh() {
        System.out.println(name);
        IdInfo idInfo = idInfoService.getByName(name);
        this.name = idInfo.getName();
        this.currentId = new AtomicLong(idInfo.getMax());
        this.step = idInfo.getStep();
        this.maxId = idInfo.getMax() + idInfo.getDelta();
        //修改数据库最大值。
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicLong getCurrentId() {
        return currentId;
    }

    public void setCurrentId(AtomicLong currentId) {
        this.currentId = currentId;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }
}
