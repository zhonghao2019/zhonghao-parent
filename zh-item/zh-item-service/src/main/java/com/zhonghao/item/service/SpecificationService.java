package com.zhonghao.item.service;

import com.zhonghao.common.enums.ExceptionEnum;
import com.zhonghao.common.exception.ZhException;
import com.zhonghao.item.mapper.SpecGroupMapper;
import com.zhonghao.item.mapper.SpecParamMapper;
import com.zhonghao.item.pojo.SpecGroup;
import com.zhonghao.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据商品分类查询规格组
     */
    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroupList = specGroupMapper.select(specGroup);
        if(CollectionUtils.isEmpty(specGroupList)) {
            throw new ZhException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        return specGroupList;
    }

    /**
     * 根据规格组查询规格参数
     */
    public List<SpecParam> queryParamByGid(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> specParamList = specParamMapper.select(specParam);
        if(CollectionUtils.isEmpty(specParamList)) {
            throw new ZhException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        return specParamList;
    }
}
