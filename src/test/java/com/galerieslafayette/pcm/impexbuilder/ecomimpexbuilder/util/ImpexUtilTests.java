package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util;


import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ImpexUtilTests {

    @Test
    public void testKeepNumericAndUnderscoreShouldNotFail() {
        Assertions.assertThat(ImpexUtil.keepNumericAndUnderscore("PSSF_6_12_1")).isEqualTo("_6_12_1");
        Assertions.assertThat(ImpexUtil.keepNumericAndUnderscore("éer1fdggh2_ççàùùm3gfdfdgfv")).isEqualTo("12_3");
    }

    @Test
    public void testToCamelCaseShouldNotFail() {
        Assertions.assertThat(ImpexUtil.toCamelCase("THIS_IS_SPARTA")).isEqualTo("thisIsSparta");
        Assertions.assertThat(ImpexUtil.toCamelCase("this_is_sparta")).isEqualTo("thisIsSparta");
    }

}
