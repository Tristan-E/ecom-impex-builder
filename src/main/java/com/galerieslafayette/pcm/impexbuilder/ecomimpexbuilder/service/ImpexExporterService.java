package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.CategoryCategoryRelation;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.*;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Service;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Service
public class ImpexExporterService {

    private PcmCategoryMapper pcmCategoryMapper;
    private ClassificationAttributeMapper classificationAttributeMapper;
    private ClassificationAttributeValueMapper classificationAttributeValueMapper;

    public ImpexExporterService(PcmCategoryMapper pcmCategoryMapper, ClassificationAttributeMapper classificationAttributeMapper,
                               ClassificationAttributeValueMapper classificationAttributeValueMapper) {
        this.pcmCategoryMapper = pcmCategoryMapper;
        this.classificationAttributeMapper = classificationAttributeMapper;
        this.classificationAttributeValueMapper = classificationAttributeValueMapper;
    }

    public ImpexExporter buildImpexExporter(Category puCategory) {
        ImpexExporter impexExporter = new ImpexExporter();

        handleCategories(puCategory, impexExporter);

        return impexExporter;
    }

    private void handleCategories(Category category, ImpexExporter impexExporter) {
        for (Category childCategory : category.getChildren()) {
            handleCategories(childCategory, impexExporter);

            impexExporter.getCategoryToCategory().add(
                    new CategoryCategoryRelation(category.getName(), childCategory.getName())
            );
        }

        addCatagoryToExporter(category, impexExporter);

        for (Attribute attribute : category.getAttributes()) {
            impexExporter.getClassificationAttributes().add(
                    classificationAttributeMapper.attributeToClassificationAttribute(attribute)
            );

            for (AttributeValue value : attribute.getValues()) {
                impexExporter.getClassificationAttributeValues().add(
                        classificationAttributeValueMapper.attributeValueToClassificationAttributeValue(value)
                );
            }
        }
    }

    private void addCatagoryToExporter(Category category, ImpexExporter impexExporter) {
        switch (category.getType()) {
            case PU:
                impexExporter.getUnivers().add(pcmCategoryMapper.categoryToPcmCategory(category));
                break;
            case PF:
                impexExporter.getFamilies().add(pcmCategoryMapper.categoryToPcmCategory(category));
                break;
            case PSF:
                impexExporter.getSubFamilies().add(pcmCategoryMapper.categoryToPcmCategory(category));
                break;
            case PSSF:
                impexExporter.getSubSubFamilies().add(pcmCategoryMapper.categoryToPcmCategory(category));
                break;
        }
    }
}
