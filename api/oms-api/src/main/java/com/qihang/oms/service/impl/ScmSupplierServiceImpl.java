package com.qihang.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.common.common.PageQuery;
import com.qihang.common.common.PageResult;
import com.qihang.oms.domain.ScmSupplier;
import com.qihang.oms.service.ScmSupplierService;
import com.qihang.oms.mapper.ScmSupplierMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author Administrator
* @description 针对表【scm_supplier】的数据库操作Service实现
* @createDate 2024-10-22 14:43:07
*/
@AllArgsConstructor
@Service
public class ScmSupplierServiceImpl extends ServiceImpl<ScmSupplierMapper, ScmSupplier>
    implements ScmSupplierService{
    private final ScmSupplierMapper mapper;

    @Override
    public PageResult<ScmSupplier> queryPageList(ScmSupplier bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ScmSupplier> queryWrapper = new LambdaQueryWrapper<ScmSupplier>();

        queryWrapper.like(StringUtils.hasText(bo.getName()), ScmSupplier::getName, bo.getName());
        Page<ScmSupplier> pages = mapper.selectPage(pageQuery.build(), queryWrapper);

        return PageResult.build(pages);
    }
}




