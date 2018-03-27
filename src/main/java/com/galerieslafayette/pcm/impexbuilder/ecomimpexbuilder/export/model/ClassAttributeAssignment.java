package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : teyma
 * @since : 18/03/2018
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClassAttributeAssignment {

    public ClassAttributeAssignment() {
        this.attributeValuesCodes = new HashSet<>();
    }

    private String classCode;
    private String attributeCode;
    private String attributeType;
    private boolean mandatory;
    private Set<String> attributeValuesCodes;
}
