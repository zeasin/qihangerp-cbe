package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName erp_goods_img
 */
@TableName(value ="erp_goods_img")
@Data
public class ErpGoodsImg implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品Id
     */
    private Long goodsId;

    /**
     * 类型
     */
    private String type;

    /**
     * 图片url
     */
    private String url;

    /**
     * 图片说明
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}