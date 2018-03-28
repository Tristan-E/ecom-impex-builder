package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassificationAttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class ClassificationAttributeValueMapper {
    public ClassificationAttributeValue attributeValueToClassificationAttributeValue(AttributeValue attributeValue) {
        if (attributeValue == null) {
            return null;
        }
        ClassificationAttributeValue classificationAttributeValue = new ClassificationAttributeValue();
        classificationAttributeValue.setCode(attributeValue.getCode());
        classificationAttributeValue.setValue(attributeValue.getValue());
        return classificationAttributeValue;
    }
}
