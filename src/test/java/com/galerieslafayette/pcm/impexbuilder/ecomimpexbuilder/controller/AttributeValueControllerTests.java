package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.AttributeValueRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class AttributeValueControllerTests {

    @InjectMocks
    private AttributeValueController attributeValueController;

    @Mock
    private AttributeValueRepository attributeValueRepository;

    @Test
    public void testGetAttributeValuesShouldFindAll() {
        attributeValueController.getAttributeValues();
        Mockito.verify(attributeValueRepository).findAll();
    }

    @Test
    public void testGetAttributeValueShouldFindOne() {
        Long testId = 13L;
        attributeValueController.getAttributeValue(testId);

        Mockito.verify(attributeValueRepository).findById(testId);
    }

    @Test
    public void testAddAttributeValueShouldSave() {
        AttributeValue attributeValue = Mockito.mock(AttributeValue.class);
        attributeValueController.addAttributeValue(attributeValue);

        Mockito.verify(attributeValueRepository).save(attributeValue);
    }

    @Test
    public void testUpdateAttributeValueShouldSave() {
        AttributeValue attributeValue = Mockito.mock(AttributeValue.class);
        attributeValueController.updateAttributeValue(attributeValue);

        Mockito.verify(attributeValueRepository).save(attributeValue);
    }

    @Test
    public void deleteAttributeValue() {
        Long testId = 132L;
        attributeValueController.deleteAttributeValue(testId);

        Mockito.verify(attributeValueRepository).deleteById(testId);
    }
}
