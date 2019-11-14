package com.zhonghao.item.mapper;

import com.zhonghao.item.pojo.Category;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

// 通用mapper
@Component
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category, Long> {
}
