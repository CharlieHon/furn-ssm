package com.charlie.furn.service.impl;

import com.charlie.furn.bean.Furn;
import com.charlie.furn.bean.FurnExample;
import com.charlie.furn.dao.FurnMapper;
import com.charlie.furn.service.FurnService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public void del(Integer id) {
        // 根据id删除家具
        furnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Furn> findAll() {
        // 通过查看 FurnMapper.xml 文件可知
        // 传入null，表示查询所有家具信息
        return furnMapper.selectByExample(null);
    }

    @Override
    public void update(Furn furn) {
        // updateByPrimaryKeySelective 可以指定修改哪些字段
        furnMapper.updateByPrimaryKeySelective(furn);
    }

    @Override
    public Furn findFurnById(Integer id) {
        // 通过传入的家具id，返回家具信息
        return furnMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Furn> findByCondition(String name) {
        FurnExample furnExample = new FurnExample();
        // 通过 criteria 对象可以设置查询条件
        FurnExample.Criteria criteria = furnExample.createCriteria();
        // 判断name是有具体内容的
        if (StringUtils.hasText(name)) {
            // 传入条件，不是 null, "", "    "
            criteria.andNameLike("%" + name + "%");
        }
        // 说明：如果name没有传值，null, "", "  " 仍然是查询所有数据
        return furnMapper.selectByExample(furnExample);
    }
}
