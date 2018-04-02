package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.*;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class ImpexExporterServiceTests {

    @InjectMocks
    private ImpexExporterService impexExporterService;

    @Mock
    private PcmCategoryMapper pcmCategoryMapper;
    @Mock
    private ClassificationAttributeMapper classificationAttributeMapper;
    @Mock
    private ClassificationAttributeValueMapper classificationAttributeValueMapper;
    @Mock
    private ClassificationClassMapper classificationClassMapper;
    @Mock
    private FieldMapper fieldMapper;

    @Test
    public void testBuildImpexExporter() throws RecursionDepthException {
        Category puCategory = initPuCategory();

        impexExporterService.buildImpexExporter(puCategory, 100);

        // TODO Unit tests
        Assertions.assertThat(true).isFalse();
    }

    private Category initPuCategory() {
        AttributeValue size1 = new AttributeValue();
        size1.setCode("SIZE-1");
        size1.setValue("Plutôt grand");

        AttributeValue size2 = new AttributeValue();
        size2.setCode("SIZE-2");
        size2.setValue("Plutôt pas trop grand");

        Attribute size = new Attribute();
        size.setName("Taille approximative");
        size.setCode("SIZE");
        size.setType(AttributeType.ENUM);
        size.setValues(new HashSet<>(Arrays.asList(size1,size2)));

        Attribute whatever = new Attribute();
        size.setName("Comme tu veux");
        size.setCode("WHATEVER");
        size.setType(AttributeType.STRING);

        Category pssfCategory = new Category();
        pssfCategory.setType(CategoryType.PSSF);
        pssfCategory.setName("Cadeaux pour lui");
        pssfCategory.setCode("PSSF1450");

        Category pssfCategory1 = new Category();
        pssfCategory1.setType(CategoryType.PSSF);
        pssfCategory1.setName("Cadeaux pour elle");
        pssfCategory1.setCode("PSSF1451");
        pssfCategory.setAttributes(new HashSet<>(Arrays.asList(whatever)));

        Category psfCategory = new Category();
        psfCategory.setType(CategoryType.PSF);
        psfCategory.setName("Cadeau pour");
        psfCategory.setCode("PSF375");
        psfCategory.setChildren(new HashSet<>(Arrays.asList(pssfCategory, pssfCategory1)));

        Category pfCategory = new Category();
        pfCategory.setType(CategoryType.PF);
        pfCategory.setName("Cadeau");
        pfCategory.setCode("PF33");
        pfCategory.setChildren(new HashSet<>(Arrays.asList(psfCategory)));
        pfCategory.setAttributes(new HashSet<>(Arrays.asList(size)));

        Category puCategory = new Category();
        puCategory.setType(CategoryType.PU);
        puCategory.setName("Maison");
        puCategory.setCode("PU15");
        puCategory.setChildren(new HashSet<>(Arrays.asList(pfCategory)));

        return puCategory;
    }
}
