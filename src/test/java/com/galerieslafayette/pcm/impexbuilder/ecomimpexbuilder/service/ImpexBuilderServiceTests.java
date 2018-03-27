package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.PcmCategoryMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImpexBuilderServiceTests {

    @InjectMocks
    private ImpexBuilderService impexBuilderService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ImpexExporterService impexExporterService;
    @Mock
    private ImpexWriterService impexWriterService;

    @Test
    public void testBuildImpex() {
        // TODO Unit tests
        Assertions.assertThat(true).isFalse();
    }
}
