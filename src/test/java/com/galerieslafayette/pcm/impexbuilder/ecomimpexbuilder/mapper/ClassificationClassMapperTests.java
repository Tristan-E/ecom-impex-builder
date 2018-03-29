package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassificationClass;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassificationClassMapperTests {

    @InjectMocks
    private ClassificationClassMapper classificationClassMapper;

    @Test
    public void testCategoryToClassificationClassShouldReturnNull() {
        Assertions.assertThat(classificationClassMapper.categoryToClassificationClass(null)).isNull();
    }

    @Test
    public void testCategoryToClassificationClassShouldWork() {
        Category category = new Category();
        category.setName("This is the name");
        // TODO implement classification code
        category.setCode("TODO");

        ClassificationClass classificationClass = classificationClassMapper.categoryToClassificationClass(category);

        Assertions.assertThat(classificationClass.getCode()).isEqualTo(category.getCode());
        Assertions.assertThat(classificationClass.getName()).isEqualTo(category.getName());
    }
}
