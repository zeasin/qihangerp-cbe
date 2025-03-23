package com.qihang.oms.controller;


import com.qihang.common.common.AjaxResult;
import com.qihang.common.common.PageQuery;
import com.qihang.common.common.TableDataInfo;
import com.qihang.oms.domain.ErpGoodsBrand;
import com.qihang.oms.service.ErpGoodsBrandService;
import com.qihang.security.common.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/goods_brand")
public class GoodsBrandController extends BaseController {
    private final ErpGoodsBrandService brandService;
    @GetMapping("/list")
    public TableDataInfo skuList(ErpGoodsBrand bo, PageQuery pageQuery)
    {
        var pageList = brandService.queryPageList(bo,pageQuery);
        return getDataTable(pageList);
    }

    /**
     * 获取商品品牌详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(brandService.getById(id));
    }

    /**
     * 新增商品品牌
     */
    @PostMapping
    public AjaxResult add(@RequestBody ErpGoodsBrand erpGoodsBrand)
    {
        erpGoodsBrand.setStatus(1);
        erpGoodsBrand.setCreateBy(getUsername());
        erpGoodsBrand.setCreateTime(new Date());
        return toAjax(brandService.save(erpGoodsBrand));
    }

    /**
     * 修改商品品牌
     */
    @PutMapping
    public AjaxResult edit(@RequestBody ErpGoodsBrand erpGoodsBrand)
    {
        erpGoodsBrand.setUpdateBy(getUsername());
        erpGoodsBrand.setUpdateTime(new Date());
        return toAjax(brandService.updateById(erpGoodsBrand));
    }

    /**
     * 删除商品品牌
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(brandService.removeByIds(Arrays.stream(ids).toList()));
    }
}
