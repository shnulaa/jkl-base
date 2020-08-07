package com.jkinvest.jkl.base.base.controller;

import org.springframework.web.bind.annotation.PathVariable;

import com.jkinvest.jkl.base.base.R;
import com.jkinvest.jkl.base.base.service.SuperCacheService;
import com.jkinvest.jkl.base.log.annotation.SysLog;
import com.jkinvest.jkl.base.security.annotation.PreAuth;

import java.io.Serializable;

/**
 * SuperCacheController
 * <p>
 * 继承该类，在SuperController类的基础上扩展了以下方法：
 * 1，get ： 根据ID查询缓存，若缓存不存在，则查询DB
 *
 * @author zuihou
 * @date 2020年03月06日11:06:46
 */
public abstract class SuperCacheController<S extends SuperCacheService<Entity>, Id extends Serializable, Entity, PageDTO, SaveDTO, UpdateDTO>
        extends SuperController<S, Id, Entity, PageDTO, SaveDTO, UpdateDTO> {

    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Override
    @SysLog("'查询:' + #id")
    @PreAuth("hasPermit('{}view')")
    public R<Entity> get(@PathVariable Id id) {
        return success(baseService.getByIdCache(id));
    }

}
