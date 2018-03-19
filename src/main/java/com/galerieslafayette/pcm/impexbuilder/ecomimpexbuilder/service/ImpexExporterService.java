package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.CategoryCategoryRelation;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.*;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Service
public class ImpexExporterService {

    private static final Logger LOG = LoggerFactory.getLogger(ImpexWriterService.class);
    private static final int maxDepth = 4;

    private PcmCategoryMapper pcmCategoryMapper;
    private ClassificationAttributeMapper classificationAttributeMapper;
    private ClassificationAttributeValueMapper classificationAttributeValueMapper;
    private ClassificationClassMapper classificationClassMapper;

    public ImpexExporterService(PcmCategoryMapper pcmCategoryMapper, ClassificationAttributeMapper classificationAttributeMapper,
                               ClassificationAttributeValueMapper classificationAttributeValueMapper, ClassificationClassMapper classificationClassMapper) {
        this.pcmCategoryMapper = pcmCategoryMapper;
        this.classificationAttributeMapper = classificationAttributeMapper;
        this.classificationAttributeValueMapper = classificationAttributeValueMapper;
        this.classificationClassMapper = classificationClassMapper;
    }

    public ImpexExporter buildImpexExporter(Category puCategory) throws RecursionDepthException{
        ImpexExporter impexExporter = new ImpexExporter();
        int categoryDepth = 1;

        LOG.info("Start building impex exporter ...");
        long start = System.currentTimeMillis();

        handleCategories(puCategory, impexExporter, categoryDepth);

        long end = System.currentTimeMillis();
        long time = end - start;

        LOG.info("End building impex exportex in {} ms.", time);

        return impexExporter;
    }

    /**
     * Recursive method with depth control.
     * Should not go out of hands :)
     * @param category
     * @param impexExporter
     * @param depth
     * @throws RecursionDepthException
     */
    private void handleCategories(Category category, ImpexExporter impexExporter, int depth) throws RecursionDepthException {
        if(depth > maxDepth) {
            throw new RecursionDepthException("Categories should only be on 4 levels (PU,PF,PSF,PSSF).");
        }

        for (Category childCategory : category.getChildren()) {
            handleCategories(childCategory, impexExporter, depth + 1);

            impexExporter.getCategoryToCategory().add(
                    new CategoryCategoryRelation(category.getCode(), childCategory.getCode())
            );
        }

        addCatagoryToExporter(category, impexExporter);

        impexExporter.getClassificationClasses().add(classificationClassMapper.categoryToClassificationClass(category));

        addAttributeAndValueToExporter(category, impexExporter);
    }

    private void addAttributeAndValueToExporter(Category category, ImpexExporter impexExporter) {
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
