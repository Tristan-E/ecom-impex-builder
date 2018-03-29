package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class ImpexExporterServiceTests {

    @InjectMocks
    private ImpexExporterService impexExporterService;

    @Mock
    private PcmCategoryMapper pcmCategoryMapper;
    @Mock
    private ClassificationAttributeMapper classificationAttributeMapper;
    @Mock
    private ClassificationAttributeValueMapper classificationAttributeValueMapper;
    @Mock
    private ClassificationClassMapper classificationClassMapper;
    @Mock
    private FieldMapper fieldMapper;

    @Test
    public void testBuildImpexExporter() {
        // TODO Unit tests
        Assertions.assertThat(true).isFalse();
    }
}
