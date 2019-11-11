package com.zhonghao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhonghao.common.enums.ExceptionEnum;
import com.zhonghao.common.exception.ZhException;
import com.zhonghao.common.vo.PageResult;
import com.zhonghao.item.mapper.BrandMapper;
import com.zhonghao.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("name", "%" + key + "%")
                    .orEqualTo("letter", key.toUpperCase());
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            throw new ZhException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        PageInfo<Brand> info = new PageInfo<>(list);
        // 返回结果
        return new PageResult<>(info.getTotal(), list);
    }

    // 品牌新增
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        int count = brandMapper.insert(brand);
        if (count != 1) {
            throw new ZhException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        // 中间表:分类品牌表
        for (Long cid : cids) {
            int count1 = brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count1 != 1) {
                throw new ZhException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }
    }
}
