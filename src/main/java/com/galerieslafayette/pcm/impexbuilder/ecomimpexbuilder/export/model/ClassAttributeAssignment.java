package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @author : teyma
 * @since : 18/03/2018
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassAttributeAssignment {
    private String classCode;
    private ClassificationAttribute attribute;
    private String attributeType;
    private boolean mandatory;
    private ArrayList<AttributeValue> attributeValues;
}
