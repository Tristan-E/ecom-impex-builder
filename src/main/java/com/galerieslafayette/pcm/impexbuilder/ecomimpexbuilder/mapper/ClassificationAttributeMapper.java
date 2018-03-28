package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassificationAttribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class ClassificationAttributeMapper {
    public ClassificationAttribute attributeToClassificationAttribute(Attribute attribute) {
        if (attribute == null) {
            return null;
        }
        ClassificationAttribute classificationAttribute = new ClassificationAttribute();
        classificationAttribute.setCode(attribute.getCode());
        classificationAttribute.setName(attribute.getName());
        classificationAttribute.setExternalId(attribute.getCode());
        return classificationAttribute;
    }
}
