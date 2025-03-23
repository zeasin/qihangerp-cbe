package com.qihang.oms.mapper;

import com.qihang.oms.domain.ErpGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qihang.oms.vo.GoodsSpecListVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【erp_goods(商品库存管理)】的数据库操作Mapper
* @createDate 2024-10-22 14:38:01
* @Entity com.qihang.oms.domain.ErpGoods
*/
public interface ErpGoodsMapper extends BaseMapper<ErpGoods> {
    List<GoodsSpecListVo> searchGoodsSpec(String keyword);
}




