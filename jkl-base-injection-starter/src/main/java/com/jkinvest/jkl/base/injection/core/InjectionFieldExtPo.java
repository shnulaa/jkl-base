package com.jkinvest.jkl.base.injection.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Objects;
import com.jkinvest.jkl.base.context.BaseContextHandler;
import com.jkinvest.jkl.base.injection.annonation.InjectionField;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zuihou
 * @date 2020年02月03日18:48:15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class InjectionFieldExtPo extends InjectionFieldPo {

    /**
     * 动态查询值
     */
    private Set<Serializable> keys = new HashSet<>();

    private String tenant;

    public InjectionFieldExtPo(InjectionField rf) {
        super(rf);
    }

    public InjectionFieldExtPo(InjectionFieldPo po, Set<Serializable> keys) {
        this.api = po.getApi();
        this.apiClass = po.getApiClass();
        this.key = po.getKey();
        this.method = po.getMethod();
        this.beanClass = po.getBeanClass();
        this.type = po.getType();
        this.keys = keys;
        this.tenant = BaseContextHandler.getTenant();
    }

    public InjectionFieldExtPo(InjectionField rf, Set<Serializable> keys) {
        super(rf);
        this.keys = keys;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InjectionFieldExtPo that = (InjectionFieldExtPo) o;

        boolean isEquals = Objects.equal(method, that.method);

        if (StrUtil.isNotEmpty(api)) {
            isEquals = isEquals && Objects.equal(api, that.api);
        } else {
            isEquals = isEquals && Objects.equal(apiClass, that.apiClass);
        }

        boolean isEqualsKeys = keys.size() == that.keys.size() && keys.containsAll(that.keys);

        return isEquals && isEqualsKeys;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(api, apiClass, method, keys);
    }
}
