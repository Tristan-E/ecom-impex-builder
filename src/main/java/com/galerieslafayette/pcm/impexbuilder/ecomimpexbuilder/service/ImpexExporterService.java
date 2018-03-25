package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.CategoryCategoryRelation;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassAttributeAssignment;
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
    private static final int MAX_DEPTH = 4;
    private static final String CLASSIFICATION_CODE_START = "CLA";

    private PcmCategoryMapper pcmCategoryMapper;
    private ClassificationAttributeMapper classificationAttributeMapper;
    private ClassificationAttributeValueMapper classificationAttributeValueMapper;
    private ClassificationClassMapper classificationClassMapper;
    private FieldMapper fieldMapper;

    public ImpexExporterService(PcmCategoryMapper pcmCategoryMapper, ClassificationAttributeMapper classificationAttributeMapper,
                                ClassificationAttributeValueMapper classificationAttributeValueMapper, ClassificationClassMapper classificationClassMapper,
                                FieldMapper fieldMapper) {
        this.pcmCategoryMapper = pcmCategoryMapper;
        this.classificationAttributeMapper = classificationAttributeMapper;
        this.classificationAttributeValueMapper = classificationAttributeValueMapper;
        this.classificationClassMapper = classificationClassMapper;
        this.fieldMapper = fieldMapper;
    }

    public ImpexExporter buildImpexExporter(Category puCategory, int classificationStartNumber) throws RecursionDepthException{
        ImpexExporter impexExporter = new ImpexExporter();
        int categoryDepth = 1;

        LOG.info("Start building impex exporter ...");
        long start = System.currentTimeMillis();

        handleCategories(puCategory, impexExporter, categoryDepth, classificationStartNumber);

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
    private void handleCategories(Category category, ImpexExporter impexExporter, int depth, int classificationNumber) throws RecursionDepthException {
        if (depth > MAX_DEPTH) {
            throw new RecursionDepthException("Categories should only be on 4 levels (PU,PF,PSF,PSSF).");
        }

        final String classificationCode = CLASSIFICATION_CODE_START + classificationNumber;

        for (Category childCategory : category.getChildren()) {
            handleCategories(childCategory, impexExporter, depth + 1, classificationNumber + 1);

            impexExporter.getCategoryToCategory().add(
                    new CategoryCategoryRelation(category.getCode(), childCategory.getCode())
            );
        }

        addCatagoryToExporter(category, impexExporter);

        impexExporter.getClassificationClasses().add(classificationClassMapper.categoryToClassificationClass(category));

        addAttributeAndValueAndAssignementToExporter(category, impexExporter, classificationCode);

        impexExporter.getClassificationToCategory().add(
                new CategoryCategoryRelation(classificationCode, category.getCode())
        );
    }

    private void addAttributeAndValueAndAssignementToExporter(Category category, ImpexExporter impexExporter, String classificationCode) {
        ClassAttributeAssignment classAttributeAssignment = null;

        for (Attribute attribute : category.getAttributes()) {
            classAttributeAssignment = new ClassAttributeAssignment();
            classAttributeAssignment.setClassCode(classificationCode);
            classAttributeAssignment.setAttributeCode(attribute.getCode());
            classAttributeAssignment.setAttributeType(attribute.getType().name());
            // TODO implement mandatory
            classAttributeAssignment.setMandatory(false);

            impexExporter.getClassificationAttributes().add(
                    classificationAttributeMapper.attributeToClassificationAttribute(attribute)
            );

            switch (attribute.getType()) {
                case ENUM:
                    impexExporter.getTypedFields().add(
                      fieldMapper.attributeToTypedField(attribute)
                    );
                    break;
                case STRING:
                    impexExporter.getUntypedFields().add(
                            fieldMapper.attributeToUntypedField(attribute)
                    );
                    break;
            }

            for (AttributeValue value : attribute.getValues()) {

                classAttributeAssignment.getAttributeValuesCodes().add(value.getCode());

                impexExporter.getClassificationAttributeValues().add(
                        classificationAttributeValueMapper.attributeValueToClassificationAttributeValue(value)
                );
            }
        }

        if (classAttributeAssignment != null) {
            impexExporter.getClassAttributeAssignments().add(classAttributeAssignment);
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
