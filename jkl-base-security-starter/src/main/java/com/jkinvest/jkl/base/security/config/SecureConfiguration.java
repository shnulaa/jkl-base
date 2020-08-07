package com.jkinvest.jkl.base.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.jkinvest.jkl.base.security.aspect.AuthAspect;
import com.jkinvest.jkl.base.security.auth.AuthFun;
import com.jkinvest.jkl.base.security.feign.UserResolverService;
import com.jkinvest.jkl.base.security.properties.ContextProperties;
import com.jkinvest.jkl.base.security.properties.SecurityProperties;

import lombok.AllArgsConstructor;

/**
 * 权限认证配置类
 *
 * @author zuihou
 * @date 2020年03月29日22:34:45
 */
@Order
@AllArgsConstructor
@EnableConfigurationProperties({SecurityProperties.class, ContextProperties.class})
public class SecureConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = SecurityProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public AuthAspect authAspect(AuthFun authFun) {
        return new AuthAspect(authFun);
    }

    @Bean("fun")
    @ConditionalOnMissingBean(AuthFun.class)
    public AuthFun getAuthFun(UserResolverService userResolverService) {
        return new AuthFun(userResolverService);
    }

    @Bean
    public GlobalMvcConfigurer getGlobalMvcConfigurer(ContextProperties contextProperties) {
        return new GlobalMvcConfigurer(contextProperties);
    }

}
