package com.qihang.auth.service;

import com.qihang.auth.domain.SysTask;
import com.qihang.common.common.PageQuery;
import com.qihang.common.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author TW
* @description 针对表【sys_task】的数据库操作Service
* @createDate 2024-03-22 19:34:41
*/
public interface SysTaskService extends IService<SysTask> {
    PageResult<SysTask> queryPageList(PageQuery pageQuery);
}
