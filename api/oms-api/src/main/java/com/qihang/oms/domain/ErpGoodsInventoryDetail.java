package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品库存明细表
 * @TableName erp_goods_inventory_detail
 */
@TableName(value ="erp_goods_inventory_detail")
@Data
public class ErpGoodsInventoryDetail implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品库存id
     */
    private Long inventoryid;

    /**
     * 入库数量
     */
    private Integer inqty;

    /**
     * 入库前数量
     */
    private Long originqty;

    /**
     * 当前库存数量
     */
    private Long currentqty;

    /**
     * 采购价
     */
    private Double purprice;

    /**
     * 入库单id
     */
    private Long entryid;

    /**
     * 入库单itemId
     */
    private Long entryitemid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 规格id
     */
    private Integer specid;

    /**
     * 商品id
     */
    private Integer goodsid;

    /**
     * 入库仓位id
     */
    private Integer inlocation;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人
     */
    private String createby;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}