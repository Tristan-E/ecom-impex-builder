package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util;

import com.google.common.base.CaseFormat;

public final class ImpexUtil {

    public static String keepNumericAndUnderscore(String str) {
        return str.replaceAll("[^0-9_]*","");
    }

    public static String toCamelCase(String upperUnderscore) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, upperUnderscore);
    }
}
