package com.qihang.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.common.common.PageQuery;
import com.qihang.common.common.PageResult;
import com.qihang.common.common.ResultVo;
import com.qihang.oms.domain.ErpGoods;
import com.qihang.oms.domain.ErpGoodsInventory;
import com.qihang.oms.domain.ErpGoodsSpec;
import com.qihang.oms.domain.ErpGoodsSpecAttr;
import com.qihang.oms.domain.bo.GoodsAddBo;
import com.qihang.oms.domain.bo.GoodsAddSkuBo;
import com.qihang.oms.mapper.ErpGoodsInventoryMapper;
import com.qihang.oms.mapper.ErpGoodsSpecAttrMapper;
import com.qihang.oms.mapper.ErpGoodsSpecMapper;
import com.qihang.oms.service.ErpGoodsService;
import com.qihang.oms.mapper.ErpGoodsMapper;
import com.qihang.oms.vo.GoodsSpecListVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
* @author Administrator
* @description 针对表【erp_goods(商品库存管理)】的数据库操作Service实现
* @createDate 2024-10-22 14:38:01
*/
@AllArgsConstructor
@Service
public class ErpGoodsServiceImpl extends ServiceImpl<ErpGoodsMapper, ErpGoods>
    implements ErpGoodsService{
    private final ErpGoodsMapper goodsMapper;
    private final ErpGoodsSpecMapper skuMapper;
    private final ErpGoodsSpecAttrMapper skuAttrMapper;
    private final ErpGoodsInventoryMapper inventoryMapper;


    @Override
    public PageResult<ErpGoodsSpec> querySkuPageList(ErpGoodsSpec bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ErpGoodsSpec> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(bo.getOuterErpSkuId()!=null,OGoodsSku::getOuterErpSkuId,bo.getOuterErpSkuId());
//        queryWrapper.eq(bo.getOuterErpGoodsId()!=null,OGoodsSku::getOuterErpGoodsId,bo.getOuterErpGoodsId());
        queryWrapper.eq(StringUtils.hasText(bo.getSpecNum()),ErpGoodsSpec::getSpecNum,bo.getSpecNum());
        Page<ErpGoodsSpec> pages = skuMapper.selectPage(pageQuery.build(), queryWrapper);

        return PageResult.build(pages);
    }

    @Override
    public PageResult<ErpGoods> queryPageList(ErpGoods bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ErpGoods> queryWrapper = new LambdaQueryWrapper<ErpGoods>();
        queryWrapper.eq(bo.getCategoryId()!=null,ErpGoods::getCategoryId,bo.getCategoryId());
        queryWrapper.eq(bo.getSupplierId()!=null,ErpGoods::getSupplierId,bo.getSupplierId());
        queryWrapper.eq(StringUtils.hasText(bo.getNumber()),ErpGoods::getNumber,bo.getNumber());
        queryWrapper.like(StringUtils.hasText(bo.getName()),ErpGoods::getName,bo.getName());
        Page<ErpGoods> pages = goodsMapper.selectPage(pageQuery.build(), queryWrapper);
        if(pages.getRecords()!=null){
            for(ErpGoods g:pages.getRecords()){
                g.setSkuList(skuMapper.selectList(new LambdaQueryWrapper<ErpGoodsSpec>().eq(ErpGoodsSpec::getGoodsId,g.getId())));
            }
        }
        return PageResult.build(pages);
    }

    @Override
    public List<GoodsSpecListVo> searchGoodsSpec(String keyword) {
        return goodsMapper.searchGoodsSpec(keyword);
    }

    @Override
    public List<ErpGoods> selectGoodsList(ErpGoods goods) {
        List<ErpGoods> list = goodsMapper.selectList(new LambdaQueryWrapper<>());
        return list;
    }

    @Override
    public ErpGoods selectGoodsById(Long id) {
        return goodsMapper.selectById(id);
    }

    @Transactional
    @Override
    public ResultVo<Long> insertGoods(String userName , GoodsAddBo bo)
    {
        if(StringUtils.isEmpty(bo.getNumber())) return ResultVo.error(500,"商品编码不能为空");
        // 查询编码是否存在
        List<ErpGoods> goodsList = goodsMapper.selectList(new LambdaQueryWrapper<ErpGoods>().eq(ErpGoods::getNumber,bo.getNumber()));
        if(goodsList!=null && goodsList.size()>0) return ResultVo.error(-1,"商品编码已存在");// return -1;

        ErpGoods goods = new ErpGoods();
        goods.setNumber(bo.getNumber());
        goods.setName(bo.getName());
        goods.setImage(bo.getImage());
//        goods.setOuterErpGoodsId(bo.getOuterErpGoodsId());
        goods.setUnitName(bo.getUnitName());
        goods.setCategoryId(bo.getCategoryId());
        goods.setBarCode(bo.getBarCode());
        goods.setStatus(1);
        goods.setLength(0.0);
        goods.setHeight(0.0);
        goods.setWidth(0.0);
        goods.setWidth1(0.0);
        goods.setWidth2(0.0);
        goods.setWidth3(0.0);
        goods.setWeight(0.0);
        goods.setDisable(0);
        goods.setPeriod(bo.getPeriod());
        goods.setPurPrice(bo.getPurPrice());
        goods.setWholePrice(bo.getWholePrice());
        goods.setRetailPrice(bo.getRetailPrice());
        goods.setUnitCost(bo.getUnitCost());
        goods.setSupplierId(bo.getSupplierId());
        goods.setBrandId(bo.getBrandId());
        goods.setLinkUrl(bo.getLinkUrl());
        goods.setLowQty(0);
        goods.setHighQty(0);
        goods.setCreateBy(userName);
        goods.setCreateTime(new Date());
//        goods.setProvince(bo.getProvince());
//        goods.setCity(bo.getCity());
//        goods.setTown(bo.getTown());

        // 1、添加主表o_goods
        goodsMapper.insert(goods);
        Long goodsId = goods.getId();
        // 2、添加规格表erp_goods_spec
        for (GoodsAddSkuBo skuBo:bo.getSpecList()) {
            ErpGoodsSpec spec = new ErpGoodsSpec();
            spec.setGoodsId(goodsId.toString());
//            spec.setOuterErpGoodsId(bo.getOuterErpGoodsId());
//            spec.setOuterErpSkuId(skuBo.getOuterErpSkuId());
            spec.setGoodsName(bo.getName());

            String skuName ="";
            if(StringUtils.hasText(skuBo.getColorValue()))
                skuName+= skuBo.getColorValue();
            if(StringUtils.hasText(skuBo.getSizeValue()))
                skuName+= " "+ skuBo.getSizeValue();
            if(StringUtils.hasText(skuBo.getStyleValue()))
                skuName+= " "+ skuBo.getStyleValue();

            spec.setSpecName(skuName);
            spec.setSpecNum(skuBo.getSpecNum());
            spec.setColorId(skuBo.getColorId());
            spec.setColorValue(skuBo.getColorValue());
            if(bo.getColorImages()!=null && StringUtils.hasText(bo.getColorImages().get(skuBo.getColorId()))){
                spec.setColorImage(bo.getColorImages().get(skuBo.getColorId()));
            }else {
                spec.setColorImage(goods.getImage());
            }
            spec.setSizeId(skuBo.getSizeId());
            spec.setSizeValue(skuBo.getSizeValue());
            spec.setStyleId(skuBo.getStyleId());
            spec.setStyleValue(skuBo.getStyleValue());
            if(bo.getPurPrice() == null){
                spec.setPurPrice(goods.getPurPrice());
            }else spec.setPurPrice(bo.getPurPrice());
            spec.setStatus(1);

            skuMapper.insert(spec);

            // 添加商品库存表
            ErpGoodsInventory inventory = new ErpGoodsInventory();
            inventory.setSpecId(spec.getId());
            inventory.setGoodsId(goodsId);
            inventory.setGoodsNum(bo.getNumber());
            inventory.setSpecNum(skuBo.getSpecNum());
            inventory.setCurrentQty(0L);
            inventory.setIsDelete(0);
            inventory.setCreateTime(new Date());
            inventory.setCreateBy("添加商品");
            inventoryMapper.insert(inventory);
        }

        // 3、添加规格属性表erp_goods_spec_attr
        if(bo.getColorValues()!=null) {
            for (Long val:bo.getColorValues()) {
                ErpGoodsSpecAttr specAttr = new ErpGoodsSpecAttr();
                specAttr.setGoodsId(goods.getId());
                specAttr.setType("color");
                specAttr.setK("颜色");
                specAttr.setKid(114L);
                specAttr.setVid(val);
                skuAttrMapper.insert(specAttr);
            }

        }
        if(bo.getSizeValues()!=null) {
            for (Long val:bo.getSizeValues()) {
                ErpGoodsSpecAttr specAttr = new ErpGoodsSpecAttr();
                specAttr.setGoodsId(goods.getId());
                specAttr.setType("size");
                specAttr.setK("尺码");
                specAttr.setKid(115L);
                specAttr.setVid(val);
                skuAttrMapper.insert(specAttr);
            }

        }
        if(bo.getColorValues()!=null) {
            for (Long val:bo.getColorValues()) {
                ErpGoodsSpecAttr specAttr = new ErpGoodsSpecAttr();
                specAttr.setGoodsId(goods.getId());
                specAttr.setType("style");
                specAttr.setK("款式");
                specAttr.setKid(116L);
                specAttr.setVid(val);
                skuAttrMapper.insert(specAttr);
            }

        }
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ResultVo.success(goodsId);
    }

    @Override
    public int updateGoods(ErpGoods goods) {
        return goodsMapper.updateById(goods);
    }

    @Transactional
    @Override
    public int deleteGoodsByIds(Long[] ids) {
        // 有业务关联的商品
        for (Long id:ids){
//            List<OOrderItem> oOrderItems = orderItemMapper.selectList(new LambdaQueryWrapper<OOrderItem>().eq(OOrderItem::getGoodsId, id));
//            if(oOrderItems.isEmpty()){
                // 删除库存
                inventoryMapper.delete(new LambdaQueryWrapper<ErpGoodsInventory>().eq(ErpGoodsInventory::getGoodsId,id));
                // 删除skuAttr
                skuAttrMapper.delete(new LambdaQueryWrapper<ErpGoodsSpecAttr>().eq(ErpGoodsSpecAttr::getGoodsId,id));
                // 删除sku
                skuMapper.delete(new LambdaQueryWrapper<ErpGoodsSpec>().eq(ErpGoodsSpec::getGoodsId,id));
                // 删除商品
                goodsMapper.deleteById(id);
//            }else{
//                return -100;//有关联的订单，不能删除
//            }
        }
        return 0;
    }

    @Override
    public int insertGoodsSku(ErpGoodsSpec goodsSku) {
//        // 是否存在
//        List<ErpGoodsSpec> oGoodsSkus = skuMapper.selectList(new LambdaQueryWrapper<ErpGoodsSpec>()
//                .eq(ErpGoodsSpec::getSpecNum, goodsSku.getSpecNum()));
//        if(oGoodsSkus==null || oGoodsSkus.size() ==0) {
//            // 查询goodsId外键
//            if (StringUtils.hasText(goodsSku.getOuterErpGoodsId())) {
//                List<OGoods> oGoods = goodsMapper.selectList(new LambdaQueryWrapper<OGoods>().eq(OGoods::getOuterErpGoodsId, goodsSku.getOuterErpGoodsId()));
//                if (oGoods != null && oGoods.size() > 0) {
//                    goodsSku.setGoodsId(oGoods.get(0).getId().toString());
//                } else {
//                    goodsSku.setGoodsId("0");
//                }
//            } else {
//                goodsSku.setGoodsId("0");
//            }
//            return skuMapper.insert(goodsSku);
//        }
        return -1;
    }



}




