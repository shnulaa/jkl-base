package com.jkinvest.jkl.base.injection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jkinvest.jkl.base.injection.aspect.InjectionResultAspect;
import com.jkinvest.jkl.base.injection.core.InjectionCore;
import com.jkinvest.jkl.base.injection.mybatis.typehandler.RemoteDataTypeHandler;
import com.jkinvest.jkl.base.injection.properties.InjectionProperties;
import com.jkinvest.jkl.base.utils.SpringUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 关联字段数据注入工具 自动配置类
 *
 * @author zuihou
 * @date 2019/09/20
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(InjectionProperties.class)
@ConditionalOnProperty(prefix = InjectionProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class InjectionDataAutoConfiguration {
    private InjectionProperties remoteProperties;

    /**
     * Spring 工具类
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringUtils beanFactoryUtils() {
        return new SpringUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = InjectionProperties.PREFIX, name = "aop-enabled", havingValue = "true", matchIfMissing = true)
    public InjectionResultAspect getRemoteAspect(InjectionCore injectionCore) {
        return new InjectionResultAspect(injectionCore);
    }

    @Bean
    @ConditionalOnMissingBean
    public InjectionCore getInjectionCore() {
        return new InjectionCore(remoteProperties);
    }

    /**
     * Mybatis 类型处理器： 处理 RemoteData 类型的字段
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public RemoteDataTypeHandler getRemoteDataTypeHandler() {
        return new RemoteDataTypeHandler();
    }
}

