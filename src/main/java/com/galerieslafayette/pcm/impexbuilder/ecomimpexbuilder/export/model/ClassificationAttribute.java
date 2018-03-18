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
public class ClassificationAttribute {
    private String code;
    private String name;
    private String externalId;
}
