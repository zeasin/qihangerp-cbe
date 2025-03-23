package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName erp_goods_spec_attr
 */
@TableName(value ="erp_goods_spec_attr")
@Data
public class ErpGoodsSpecAttr implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long goodsId;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private String k;

    /**
     * 
     */
    private Long kid;

    /**
     * 
     */
    private Long vid;

    /**
     * 
     */
    private String v;

    /**
     * 
     */
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}