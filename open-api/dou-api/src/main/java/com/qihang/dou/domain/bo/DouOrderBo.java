package com.qihang.dou.domain.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DouOrderBo implements Serializable {
    private String orderId;
    private Long skuId;
    private Long erpGoodsSkuId;
    private Integer shopId;
    private Integer orderStatus;
    private Integer erpSendStatus;
    private String startTime;
    private String endTime;
}
