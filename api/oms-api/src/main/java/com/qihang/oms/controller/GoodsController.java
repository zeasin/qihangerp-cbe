package com.qihang.oms.controller;



import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qihang.common.common.*;
import com.qihang.oms.domain.ErpGoods;
import com.qihang.oms.domain.ErpGoodsSpec;
import com.qihang.oms.domain.bo.GoodsAddBo;
import com.qihang.oms.service.ErpGoodsService;
import com.qihang.oms.service.ErpGoodsSpecService;
import com.qihang.oms.vo.GoodsSpecListVo;
import com.qihang.security.common.BaseController;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品管理Controller
 * 
 * @author qihang
 * @date 2023-12-29
 */
@AllArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController
{
    private final ErpGoodsService goodsService;
    private final ErpGoodsSpecService skuService;

    /**
     * 搜索商品SKU
     * 条件：商品编码、SKU、商品名称
     */
    @GetMapping("/searchSku")
    public TableDataInfo searchSkuBy(String keyword)
    {
        List<GoodsSpecListVo> list = goodsService.searchGoodsSpec(keyword);
        return getDataTable(list);
    }

    @GetMapping("/sku_list")
    public TableDataInfo skuList(ErpGoodsSpec bo, PageQuery pageQuery)
    {
        var pageList = goodsService.querySkuPageList(bo,pageQuery);
        return getDataTable(pageList);
    }

    /**
     * 查询商品管理列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpGoods goods, PageQuery pageQuery)
    {
        PageResult<ErpGoods> pageList = goodsService.queryPageList(goods, pageQuery);
        return getDataTable(pageList);
    }

    /**
     * 获取商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(goodsService.selectGoodsById(id));
    }
    /**
     * 获取商品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:query')")
    @GetMapping(value = "/sku/{id}")
    public AjaxResult getSkuInfo(@PathVariable("id") Long id)
    {
        return success(skuService.getById(id));
    }
    /**
     * 新增商品管理
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:add')")
    @PostMapping("/add")
    public AjaxResult add(@RequestBody GoodsAddBo goods)
    {
        ResultVo<Long> resultVo = goodsService.insertGoods(getUsername(), goods);
        if(resultVo.getCode()!=0) return AjaxResult.error(resultVo.getMsg());
        else return AjaxResult.success(resultVo.getData());
//        goods.setCreateBy(getUsername());
//        int result = goodsService.insertGoods(goods);
//        if(result == -1) new AjaxResult(501,"商品编码已存在");
//        return toAjax(1);
    }



    /**
     * 修改商品管理
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody ErpGoods goods)
    {
        return toAjax(goodsService.updateGoods(goods));
    }
    @PutMapping("/sku")
    public AjaxResult editSku(@RequestBody ErpGoodsSpec sku)
    {
        return toAjax(skuService.updateById(sku));
    }

    /**
     * 删除商品管理
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:remove')")
    @DeleteMapping("/del/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        int result = goodsService.deleteGoodsByIds(ids);
        if(result==0) return AjaxResult.success();
        else if (result==-100) return AjaxResult.error("有关联的订单，不能删除！");
        else return AjaxResult.error();
    }


}
