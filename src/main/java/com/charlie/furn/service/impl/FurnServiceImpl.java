package com.charlie.furn.service.impl;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.dao.FurnMapper;
import com.charlie.furn.service.FurnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FurnServiceImpl implements FurnService {

    // 注入/装配FurnMapper接口对象(代理对象)
    @Resource
    private FurnMapper furnMapper;

    @Override
    public void save(Furn furn) {
        // 1. 这里使用 insertSelective
        // 2. 因为furn表的id是自增长，就使用 insertSelective
        furnMapper.insertSelective(furn);
    }
}
