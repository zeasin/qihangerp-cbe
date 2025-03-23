package com.qihang.oms.service;

import com.qihang.common.common.PageQuery;
import com.qihang.common.common.PageResult;
import com.qihang.oms.domain.ScmSupplier;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【scm_supplier】的数据库操作Service
* @createDate 2024-10-22 14:43:07
*/
public interface ScmSupplierService extends IService<ScmSupplier> {
    PageResult<ScmSupplier> queryPageList(ScmSupplier bo, PageQuery pageQuery);
}
