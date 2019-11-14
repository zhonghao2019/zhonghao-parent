package com.zhonghao.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhonghao.common.enums.ExceptionEnum;
import com.zhonghao.common.exception.ZhException;
import com.zhonghao.common.vo.PageResult;
import com.zhonghao.item.mapper.SpuDetailMapper;
import com.zhonghao.item.mapper.SpuMapper;
import com.zhonghao.item.pojo.Category;
import com.zhonghao.item.pojo.Spu;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        Example example = new Example(Spu.class);
        // 查询
        Example.Criteria criteria = example.createCriteria();
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        if (StringUtils.isNotEmpty(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        // 排序
        example.setOrderByClause("last_update_time DESC");
        List<Spu> spuList = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spuList)) {
            throw new ZhException(ExceptionEnum.SPU_NOT_FOUND);
        }
        // 解析分类和品牌的名称
        loadCategoryAndBrandName(spuList);
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);
        return new PageResult<>(pageInfo.getTotal(), spuList);
    }

    private void loadCategoryAndBrandName(List<Spu> spuList) {
        for (Spu spu : spuList) {
            // 处理分类名称
            List<String> names = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            // 集合转字符串
            spu.setCname(StringUtils.join(names, "/"));
            // 处理品牌名称
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());
        }
    }
}
