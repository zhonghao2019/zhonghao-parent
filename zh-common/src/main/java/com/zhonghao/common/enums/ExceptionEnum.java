package com.zhonghao.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
// 异常枚举
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    CATEGORY_NOT_FOUND(404, "商品分类为空"),
    BRAND_NOT_FOUND(404, "品牌没有找到"),
    BRAND_SAVE_ERROR(500, "品牌新增失败"),
    SPEC_GROUP_NOT_FOUND(404, "规格组没有找到"),
    SPEC_PARAM_NOT_FOUND(404, "规格参数没有找到"),
    SPU_NOT_FOUND(404, "商品没有找到"),
    ;

    private int code;
    private String msg;
}