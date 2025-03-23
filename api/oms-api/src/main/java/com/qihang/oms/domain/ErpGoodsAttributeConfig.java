package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品属性表
 * @TableName erp_goods_attribute_config
 */
@TableName(value ="erp_goods_attribute_config")
@Data
public class ErpGoodsAttributeConfig implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类id（0为所有共用）
     */
    private Integer categoryId;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}