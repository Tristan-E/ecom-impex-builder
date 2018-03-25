package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : teyma
 * @since : 25/03/2018
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TypedField extends Field{
    private String strategyBeanName;
}
