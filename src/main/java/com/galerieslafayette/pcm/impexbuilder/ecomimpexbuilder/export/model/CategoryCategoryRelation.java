package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : teyma
 * @since : 18/03/2018
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CategoryCategoryRelation {

    private String sourceCode;
    private String destinationCode;

    public CategoryCategoryRelation(String sourceCode, String destinationCode) {
        this.sourceCode = sourceCode;
        this.destinationCode = destinationCode;
    }
}
