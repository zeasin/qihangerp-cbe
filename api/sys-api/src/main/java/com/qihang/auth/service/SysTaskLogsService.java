package com.qihang.auth.service;

import com.qihang.auth.domain.SysTaskLogs;
import com.qihang.common.common.PageQuery;
import com.qihang.common.common.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author TW
* @description 针对表【sys_task_logs】的数据库操作Service
* @createDate 2024-03-22 20:07:56
*/
public interface SysTaskLogsService extends IService<SysTaskLogs> {
    PageResult<SysTaskLogs> queryPageList(Integer taskId,PageQuery pageQuery);
}
