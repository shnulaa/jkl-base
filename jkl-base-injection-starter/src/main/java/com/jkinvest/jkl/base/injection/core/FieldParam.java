package com.jkinvest.jkl.base.injection.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import com.jkinvest.jkl.base.injection.annonation.InjectionField;

/**
 * @author zuihou
 * @date 2020/5/8 下午9:19
 */
@Data
@AllArgsConstructor
public class FieldParam {
    private InjectionField anno;
    private Serializable queryKey;
    private Object curField;
}
