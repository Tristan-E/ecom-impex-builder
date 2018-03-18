package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util;

public final class ImpexUtil {

    public static String keepNumericAndUnderscore(String str) {
        return str.replaceAll("[^0-9_]*","");
    }
}
