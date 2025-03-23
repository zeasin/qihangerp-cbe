package com.qihang.oms.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName erp_goods_category
 */
@TableName(value ="erp_goods_category")
@Data
public class ErpGoodsCategory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类编码
     */
    private String number;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 
     */
    private String remark;

    /**
     * 上架分类id
     */
    private Integer parentId;

    /**
     * 分类路径
     */
    private String path;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 图片
     */
    private String image;

    /**
     * 0正常  1删除
     */
    private Integer isdelete;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}