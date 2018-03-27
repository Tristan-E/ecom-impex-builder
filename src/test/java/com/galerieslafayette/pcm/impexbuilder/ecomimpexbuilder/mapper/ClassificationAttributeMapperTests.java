package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller.ImpexBuilderController;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service.ImpexBuilderService;
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
public class ClassificationAttributeMapperTests {

    @InjectMocks
    private ClassificationAttributeMapper classificationAttributeMapper;

    @Test
    public void testAttributeToClassificationAttribute() {
        // TODO Unit tests
        Assertions.assertThat(true).isFalse();
    }
}
