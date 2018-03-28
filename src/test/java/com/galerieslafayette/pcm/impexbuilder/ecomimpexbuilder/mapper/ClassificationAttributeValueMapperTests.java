package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassificationAttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
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
public class ClassificationAttributeValueMapperTests {

    @InjectMocks
    private ClassificationAttributeValueMapper classificationAttributeValueMapper;

    @Test
    public void testAttributeValueToClassificationAttributeValueShouldReturnNull() {
        Assertions.assertThat(classificationAttributeValueMapper.attributeValueToClassificationAttributeValue(null)).isNull();
    }

    @Test
    public void testAttributeValueToClassificationAttributeValueShouldWork() {
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setCode("Attribute value code");
        attributeValue.setValue("Attribute value value, LOLILOL");

        ClassificationAttributeValue classificationAttributeValue = classificationAttributeValueMapper.attributeValueToClassificationAttributeValue(attributeValue);

        Assertions.assertThat(classificationAttributeValue.getCode()).isEqualTo(attributeValue.getCode());
        Assertions.assertThat(classificationAttributeValue.getValue()).isEqualTo(attributeValue.getValue());
    }
}
