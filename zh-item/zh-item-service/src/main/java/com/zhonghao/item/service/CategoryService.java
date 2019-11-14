package com.zhonghao.item.service;

import com.zhonghao.common.enums.ExceptionEnum;
import com.zhonghao.common.exception.ZhException;
import com.zhonghao.item.mapper.CategoryMapper;
import com.zhonghao.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询分类
     */
//    返回业务数据
    public List<Category> queryCategoryListByPid(Long pid) {
//        设置查询条件
        Category category = new Category();
        category.setParentId(pid);
        List<Category> list = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(list)) {
            throw new ZhException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    /**
     * 根据分类id查询分类
     */
    public List<Category> queryByIds(List<Long> ids) {
//        根据多个id查询集合
        List<Category> list = categoryMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(list)) {
            throw new ZhException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
