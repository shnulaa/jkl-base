package com.jkinvest.jkl.base.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.jkinvest.jkl.base.jwt.JwtProperties.PREFIX;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 认证服务端 属性
 *
 * @author zuihou
 * @date 2018/11/20
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = PREFIX)
public class JwtProperties {
    public static final String PREFIX = "authentication";

    /**
     * 过期时间 2h
     */
    private Long expire = 7200L;
    /**
     * 刷新token的过期时间 8h
     */
    private Long refreshExpire = 28800L;

}
