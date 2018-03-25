package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.Field;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.TypedField;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.UntypedField;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util.ImpexUtil;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 25/03/2018
 */
@Component
public class FieldMapper {

    private static final String SETTER_STRATEGY = "SetterStrategy";
    private static final String TYPED_FIELD_VALUE_LOADER_STRATEGY = "TypedFieldValueLoaderStrategy";

    public UntypedField attributeToUntypedField(Attribute attribute) {
        UntypedField untypedField= new UntypedField();
        fillField(untypedField, attribute);
        return untypedField;
    }

    public TypedField attributeToTypedField(Attribute attribute) {
        TypedField typedField = new TypedField();
        fillField(typedField, attribute);
        typedField.setStrategyBeanName(ImpexUtil.toCamelCase(attribute.getCode()) + TYPED_FIELD_VALUE_LOADER_STRATEGY);
        return typedField;
    }

    private <T extends Field> void fillField(T field, Attribute attribute) {
        field.setCode(attribute.getCode().toLowerCase());
        field.setName(attribute.getName());
        field.setMandatory(false);
        field.setActivated(true);
        field.setObjectType("PRODUCT");
        field.setFieldSetterStrategyBeanName(ImpexUtil.toCamelCase(attribute.getCode()) + SETTER_STRATEGY);
        field.setDependencies("PSSF");
        field.setUniverses("TODO");
        field.setGroup("SPECIFIC_ATTRIBUTES");
        field.setClassificationAttribute(attribute.getCode());
    }
}
