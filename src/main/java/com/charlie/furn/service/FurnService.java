package com.charlie.furn.service;

import com.charlie.furn.bean.Furn;

import java.util.List;

public interface FurnService {
    // 添加
    public void save(Furn furn);
    // 查询所有家具信息
    public List<Furn> findAll();
    // 修改家具信息
    public void update(Furn furn);
    // 删除家具信息
    public void del(Integer id);
    // 根据家具id，获取家具信息
    public Furn findFurnById(Integer id);
    // 根据家具名称，进行条件查询
    public List<Furn> findByCondition(String name);
}
