package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName erp_goods_category_attribute_value
 */
@TableName(value ="erp_goods_category_attribute_value")
@Data
public class ErpGoodsCategoryAttributeValue implements Serializable {
    /**
     * 主键，属性值id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 属性id
     */
    private Integer categoryAttributeId;

    /**
     * 属性值文本
     */
    private String value;

    /**
     * 生成SKU的编码
     */
    private String skuCode;

    /**
     * 
     */
    private Integer ordernum;

    /**
     * 
     */
    private Integer isdelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}