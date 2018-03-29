package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller.ImpexBuilderController;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassificationAttribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service.ImpexBuilderService;
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
public class ClassificationAttributeMapperTests {

    @InjectMocks
    private ClassificationAttributeMapper classificationAttributeMapper;

    @Test
    public void testAttributeToClassificationAttributeShouldReturnNull() {
        Assertions.assertThat(classificationAttributeMapper.attributeToClassificationAttribute(null)).isNull();
    }

    @Test
    public void testAttributeToClassificationAttributeShouldWork() {
        Attribute attribute = new Attribute();
        attribute.setCode("This is an attribute code");
        attribute.setName("This is an attribute name");

        ClassificationAttribute classificationAttribute = classificationAttributeMapper.attributeToClassificationAttribute(attribute);

        Assertions.assertThat(classificationAttribute.getCode()).isEqualTo(attribute.getCode());
        Assertions.assertThat(classificationAttribute.getName()).isEqualTo(attribute.getName());
        Assertions.assertThat(classificationAttribute.getExternalId()).isEqualTo(attribute.getCode());
    }
}
