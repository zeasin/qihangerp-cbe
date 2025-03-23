package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName erp_goods_category_attribute
 */
@TableName(value ="erp_goods_category_attribute")
@Data
public class ErpGoodsCategoryAttribute implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer categoryId;

    /**
     * 类型：0属性1规格
     */
    private Integer type;

    /**
     * '属性名'
     */
    private String title;

    /**
     * 固定值color颜色size尺码style款式
     */
    private String code;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}