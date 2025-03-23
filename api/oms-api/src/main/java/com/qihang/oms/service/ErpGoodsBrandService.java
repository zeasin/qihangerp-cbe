package com.qihang.oms.service;

import com.qihang.common.common.PageQuery;
import com.qihang.common.common.PageResult;
import com.qihang.oms.domain.ErpGoodsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【erp_goods_brand】的数据库操作Service
* @createDate 2024-10-22 14:38:01
*/
public interface ErpGoodsBrandService extends IService<ErpGoodsBrand> {
    PageResult<ErpGoodsBrand> queryPageList(ErpGoodsBrand bo, PageQuery pageQuery);
}
