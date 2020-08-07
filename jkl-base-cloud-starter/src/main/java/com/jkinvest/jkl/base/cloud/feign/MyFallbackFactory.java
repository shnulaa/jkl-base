package com.jkinvest.jkl.base.cloud.feign;

import org.springframework.cglib.proxy.Enhancer;

import feign.Target;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;

/**
 * 全局默认 Fallback，避免写过多fallback类
 *
 * @param <T> 泛型标记
 * @author zuihou
 */
@AllArgsConstructor
public class MyFallbackFactory<T> implements FallbackFactory<T> {
    private final Target<T> target;

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new MyFeignFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }
}
