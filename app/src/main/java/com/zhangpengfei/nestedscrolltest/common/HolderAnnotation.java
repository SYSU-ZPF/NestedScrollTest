package com.zhangpengfei.nestedscrolltest.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface HolderAnnotation {
    int layoutId();
}
