package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmCategory;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util.ImpexUtil;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PcmCategoryMapperTests {

    @InjectMocks @Spy
    private PcmCategoryMapper pcmCategoryMapper;

    private static final String KEEP_NUMERIC_IS_CALLED = "KEEP_NUMERIC_IS_CALLED";

    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(KEEP_NUMERIC_IS_CALLED).when(pcmCategoryMapper).keepNumericAndUnderscore(Mockito.anyString());
    }

    @Test
    public void testCategoryToPcmCategoryShouldReturnNull() {
        Assertions.assertThat(pcmCategoryMapper.categoryToPcmCategory(null)).isNull();
    }

    @Test
    public void testCategoryToPcmCategoryShouldWork() {
        Category category = new Category();
        category.setCode("categoryCode");
        category.setName("categoryName");

        PcmCategory pcmCategory = pcmCategoryMapper.categoryToPcmCategory(category);

        Assertions.assertThat(category.getCode()).isEqualTo(pcmCategory.getCode());
        Assertions.assertThat(category.getName()).isEqualTo(pcmCategory.getName());
        Assertions.assertThat(KEEP_NUMERIC_IS_CALLED).isEqualTo(pcmCategory.getExternalCode());
    }
}
