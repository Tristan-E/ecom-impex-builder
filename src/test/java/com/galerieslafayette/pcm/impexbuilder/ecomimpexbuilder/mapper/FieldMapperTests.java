package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.Field;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.TypedField;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.UntypedField;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util.ImpexUtil;
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
public class FieldMapperTests {

    @InjectMocks
    private FieldMapper fieldMapper;

    private static final String SETTER_STRATEGY = "SetterStrategy";
    private static final String TYPED_FIELD_VALUE_LOADER_STRATEGY = "TypedFieldValueLoaderStrategy";

    @Test
    public void testAttributeToUntypedFieldShouldReturnNull() {
        Assertions.assertThat(fieldMapper.attributeToUntypedField(null)).isNull();
    }

    @Test
    public void testAttributeToUntypedField() {
        Attribute attribute = initAttribute();
        UntypedField untypedField = fieldMapper.attributeToUntypedField(attribute);
        verifyFieldAttribute(untypedField, attribute);
    }

    @Test
    public void testAttributeToTypedFieldShouldReturnNull() {
        Assertions.assertThat(fieldMapper.attributeToTypedField(null)).isNull();
    }

    @Test
    public void testAttributeToTypedField() {
        Attribute attribute = initAttribute();
        TypedField typedField = fieldMapper.attributeToTypedField(attribute);
        verifyFieldAttribute(typedField, attribute);
        Assertions.assertThat(typedField.getStrategyBeanName()).isEqualTo(ImpexUtil.toCamelCase(attribute.getCode()) + TYPED_FIELD_VALUE_LOADER_STRATEGY);
    }

    private Attribute initAttribute() {
        Attribute attribute = new Attribute();
        attribute.setCode("What is love");
        attribute.setName("Oh baby don't hurt me ...");
        return attribute;
    }

    private <T extends Field> void verifyFieldAttribute(T field, Attribute attribute) {
        Assertions.assertThat(field.getCode()).isEqualTo(attribute.getCode().toLowerCase());
        Assertions.assertThat(field.getName()).isEqualTo(attribute.getName());
        // TODO Implement mandatory
        Assertions.assertThat(field.isMandatory()).isEqualTo(false);
        // TODO Implement Activated
        Assertions.assertThat(field.isActivated()).isEqualTo(true);
        // TODO Implement object type
        Assertions.assertThat(field.getObjectType()).isEqualTo("PRODUCT");
        Assertions.assertThat(field.getFieldSetterStrategyBeanName()).isEqualTo(ImpexUtil.toCamelCase(attribute.getCode()) + SETTER_STRATEGY);
        // TODO Implement dependencies
        Assertions.assertThat(field.getDependencies()).isEqualTo("PSSF");
        // TODO Implement Universes
        Assertions.assertThat(field.getUniverses()).isEqualTo("TODO");
        // TODO Implement Group
        Assertions.assertThat(field.getGroup()).isEqualTo("SPECIFIC_ATTRIBUTES");
        Assertions.assertThat(field.getClassificationAttribute()).isEqualTo(attribute.getCode());
    }
}
