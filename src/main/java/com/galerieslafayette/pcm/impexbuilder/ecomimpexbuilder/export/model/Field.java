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
public class Field {
    private String code;
    private String name;
    private boolean mandatory;
    private boolean activated;
    private String objectType;
    private String fieldSetterStrategyBeanName;
    private String dependencies;
    private String universes;
    private String group;
    private String classificationAttribute;
}
