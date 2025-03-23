package com.qihang.oms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qihang.common.common.AjaxResult;
import com.qihang.common.common.TableDataInfo;
import com.qihang.oms.domain.ErpGoodsCategory;
import com.qihang.oms.domain.ErpGoodsCategoryAttribute;
import com.qihang.oms.domain.ErpGoodsCategoryAttributeValue;
import com.qihang.oms.service.ErpGoodsCategoryAttributeService;
import com.qihang.oms.service.ErpGoodsCategoryAttributeValueService;
import com.qihang.oms.service.ErpGoodsCategoryService;
import com.qihang.security.common.BaseController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/goods_category")
public class GoodsCategoryController  extends BaseController {
    private final ErpGoodsCategoryService categoryService;
    private final ErpGoodsCategoryAttributeService categoryAttributeService;
    private final ErpGoodsCategoryAttributeValueService categoryAttributeValueService;

    @GetMapping("/list")
    public TableDataInfo categoryList()
    {
        var pageList = categoryService.list();
        return getDataTable(pageList);
    }

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(categoryService.getById(id));
    }

    @PostMapping
    public AjaxResult add(@RequestBody ErpGoodsCategory category)
    {
        category.setCreateBy(getUsername());
        category.setCreateTime(new Date());
        category.setIsdelete(0);
        return toAjax(categoryService.save(category));
    }

    @PutMapping
    public AjaxResult edit(@RequestBody ErpGoodsCategory category)
    {
        category.setUpdateBy(getUsername());
        category.setUpdateTime(new Date());
        return toAjax(categoryService.updateById(category));
    }
    /**
     * 删除分类
     * @param ids
     * @return
     */
    @DeleteMapping("/del/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryService.removeByIds(Arrays.stream(ids).toList()));
    }

    /**
     * 分类属性列表
     * @param categoryId
     * @return
     */
    @GetMapping("/attribute_list")
    public TableDataInfo attributeList(Integer categoryId)
    {
        var pageList = categoryAttributeService.list(new LambdaQueryWrapper<ErpGoodsCategoryAttribute>().eq(ErpGoodsCategoryAttribute::getCategoryId,categoryId));
        return getDataTable(pageList);
    }

    /**
     * 分类属性添加
     * @param attribute
     * @return
     */
    @PostMapping("/attribute_add")
    public AjaxResult attributeAdd(@RequestBody ErpGoodsCategoryAttribute attribute)
    {
        return toAjax(categoryAttributeService.save(attribute));
    }

    @GetMapping(value = "/attribute/{id}")
    public AjaxResult getAttributeInfo(@PathVariable("id") Long id)
    {
        return success(categoryAttributeService.getById(id));
    }

    @PutMapping("/attribute")
    public AjaxResult attributeEdit(@RequestBody ErpGoodsCategoryAttribute attribute)
    {
        return toAjax(categoryAttributeService.updateById(attribute));
    }
    @DeleteMapping("/attribute/{ids}")
    public AjaxResult attributeRemove(@PathVariable Long[] ids)
    {
        return toAjax(categoryAttributeService.removeByIds(Arrays.stream(ids).toList()));
    }

    @GetMapping("/attribute_value_list")
    public TableDataInfo attributeValueList(Integer categoryAttributeId)
    {
        var pageList = categoryAttributeValueService.list(
                new LambdaQueryWrapper<ErpGoodsCategoryAttributeValue>().eq(ErpGoodsCategoryAttributeValue::getCategoryAttributeId,categoryAttributeId));
        return getDataTable(pageList);

    }

    @PostMapping("/attribute_value")
    public AjaxResult add(@RequestBody ErpGoodsCategoryAttributeValue attributeValue)
    {
        return toAjax(categoryAttributeValueService.save(attributeValue));
    }
    @GetMapping(value = "/attribute_value/{id}")
    public AjaxResult getAttributeValueInfo(@PathVariable("id") Long id)
    {
        return success(categoryAttributeValueService.getById(id));
    }

    @PutMapping("/attribute_value")
    public AjaxResult attributeValueEdit(@RequestBody ErpGoodsCategoryAttributeValue attributeValue)
    {
        return toAjax(categoryAttributeValueService.updateById(attributeValue));
    }

    /**
     * 删除商品分类属性值
     */
    @DeleteMapping("/attribute_value/{ids}")
    public AjaxResult removeAttributeValue(@PathVariable Long[] ids)
    {
        return toAjax(categoryAttributeValueService.removeByIds(Arrays.stream(ids).toList()));
    }
}
