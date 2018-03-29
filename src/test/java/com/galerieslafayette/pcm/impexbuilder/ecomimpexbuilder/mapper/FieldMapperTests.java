package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.Field;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.TypedField;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.UntypedField;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class FieldMapperTests {

    @InjectMocks @Spy
    private FieldMapper fieldMapper;
    private static final String TO_CAMEL_CASE_IS_CALLED = "TO_CAMEL_CASE_IS_CALLED";

    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(TO_CAMEL_CASE_IS_CALLED).when(fieldMapper).toCamelCaseAndSuffix(Mockito.anyString(), Mockito.anyString());
    }

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
        Assertions.assertThat(typedField.getStrategyBeanName()).isEqualTo(TO_CAMEL_CASE_IS_CALLED);
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
        Assertions.assertThat(field.getFieldSetterStrategyBeanName()).isEqualTo(TO_CAMEL_CASE_IS_CALLED);
        // TODO Implement dependencies
        Assertions.assertThat(field.getDependencies()).isEqualTo("PSSF");
        // TODO Implement Universes
        Assertions.assertThat(field.getUniverses()).isEqualTo("TODO");
        // TODO Implement Group
        Assertions.assertThat(field.getGroup()).isEqualTo("SPECIFIC_ATTRIBUTES");
        Assertions.assertThat(field.getClassificationAttribute()).isEqualTo(attribute.getCode());
    }
}
