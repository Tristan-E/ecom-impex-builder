package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassificationClassMapperTests {

    @InjectMocks
    private ClassificationClassMapper classificationClassMapper;

    @Test
    public void testCategoryToClassificationClass() {
        // TODO Unit tests
        Assertions.assertThat(true).isFalse();
    }
}
