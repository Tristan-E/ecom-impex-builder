package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexPrinter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Service
public class ImpexWriterService {

    private static final String EMPTY_STRING = "";

    private static final Logger LOG = LoggerFactory.getLogger(ImpexWriterService.class);

    @Value( "${impex.writer.csv.separator}" )
    private char csvSeparator;

    @Value( "${impex.writer.folderpath}" )
    private String folderPath;

    public void write(ImpexExporter impexExporter) throws IOException {

        LOG.info("Start exporting impex file ...");
        long start = System.currentTimeMillis();

        writePcmCategory(impexExporter.getUnivers(), ImpexConstant.INSERT_PU_HEADING, ImpexConstant.PU_FILE_NAME);
        writePcmCategory(impexExporter.getFamilies(), ImpexConstant.INSERT_PF_HEADING, ImpexConstant.PF_FILE_NAME);
        writePcmCategory(impexExporter.getSubFamilies(), ImpexConstant.INSERT_PSF_HEADING, ImpexConstant.PSF_FILE_NAME);
        writePcmCategory(impexExporter.getSubSubFamilies(), ImpexConstant.INSERT_PSSF_HEADING, ImpexConstant.PSSF_FILE_NAME);

        writeMappingCategories(impexExporter.getCategoryToCategory());
        writeClassifications(impexExporter.getClassificationClasses());
        writeAttributes(impexExporter.getClassificationAttributes());
        writeAttributeValues(impexExporter.getClassificationAttributeValues());
        writeClassAttributeAssignement(impexExporter.getClassAttributeAssignments());
        writeClassificationToCatgory(impexExporter.getClassificationToCategory());
        writeFields(impexExporter.getUntypedFields(), impexExporter.getTypedFields());

        long end = System.currentTimeMillis();
        long time = end - start;

        LOG.info("End exporting impex file in {} ms.", time);
    }

    private void writePcmCategory(Set<PcmCategory> pcmCategories, String insertHeader, String fileName) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(fileName);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printCategoryHeader(impexPrinter);

            impexPrinter.println(insertHeader);

            for (PcmCategory category : pcmCategories) {
                impexPrinter.printRecord(category.getCode(), category.getName(), category.getExternalCode());
            }
            impexPrinter.flush();
        }
    }

    private void writeMappingCategories(Set<CategoryCategoryRelation> categoryToCategorySet) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.MAPPING_CATEGORY_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printCategoryToCategoryHeader(impexPrinter);

            impexPrinter.println(ImpexConstant.INSERT_CATEGORY_TO_CATEGORY);

            for (CategoryCategoryRelation categoryToCategory : categoryToCategorySet) {
                impexPrinter.printRecord(categoryToCategory.getSourceCode(), categoryToCategory.getDestinationCode());
            }
            impexPrinter.flush();
        }
    }

    private void writeClassifications(Set<ClassificationClass> classifications) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.CLASSIFICATION_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printClassificationHeader(impexPrinter);

            impexPrinter.println(ImpexConstant.INSERT_CLASSIFICATION_CLASS);

            for (ClassificationClass classification : classifications) {
                impexPrinter.printRecord(EMPTY_STRING, classification.getCode(), classification.getName());
            }
            impexPrinter.flush();
        }
    }

    private void writeAttributes(Set<ClassificationAttribute> attributes) throws IOException{
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.ATTRIBUTE_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printCategoryToCategoryHeader(impexPrinter);

            impexPrinter.println(ImpexConstant.INSERT_CLASSIFICATION_ATTRIBUTE);

            for (ClassificationAttribute attribute : attributes) {
                impexPrinter.printRecord(attribute.getCode(), attribute.getName(), attribute.getExternalId());
            }
            impexPrinter.flush();
        }
    }

    private void writeAttributeValues(Set<ClassificationAttributeValue> attributeValues) throws IOException{
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.ATTRIBUTE_VALUE_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printAttributeAndAttributeValueHeader(impexPrinter);

            impexPrinter.println(ImpexConstant.INSERT_CLASSIFICATION_ATTRIBUTE_VALUE);

            for (ClassificationAttributeValue attributeValue : attributeValues) {
                impexPrinter.printRecord(attributeValue.getCode(), attributeValue.getValue());
            }
            impexPrinter.flush();
        }
    }

    private void writeClassAttributeAssignement(Set<ClassAttributeAssignment> classAttributeAssignments) throws IOException{
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.CLASS_ATTRIBUTE_ASSIGNEMENT_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printClassificationAssignementHeader(impexPrinter);

            impexPrinter.println(ImpexConstant.INSERT_CLASS_ATTRIBUTE_ASSIGNEMENT);

            for (ClassAttributeAssignment classAttributeAssignment : classAttributeAssignments) {
                impexPrinter.printRecord(
                        classAttributeAssignment.getClassCode(),
                        classAttributeAssignment.getAttributeCode(),
                        classAttributeAssignment.getAttributeType(),
                        classAttributeAssignment.isMandatory(),
                        classAttributeAssignment.getAttributeValuesCodes()
                );
            }
            impexPrinter.flush();
        }
    }

    private void writeClassificationToCatgory(Set<CategoryCategoryRelation> classToCategories) throws IOException{
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.MAPPING_CLA_CATEGORY_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {

            impexPrinter.println(ImpexConstant.INSERT_CATEGORY_TO_CLASSIFICATION);

            for (CategoryCategoryRelation classToCategory : classToCategories) {
                impexPrinter.printRecord(
                        "CategoryCategoryRelation",
                        0,
                        0,
                        "glpcmClassification:1.0:" + classToCategory.getSourceCode(),
                        "glpcmProductCatalog:Staged:" + classToCategory.getDestinationCode()
                );
            }
            impexPrinter.flush();
        }
    }

    private void writeFields(Set<UntypedField> untypedFields, Set<TypedField> typedFields) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.FIELD_FILE_NAME);
                ImpexPrinter impexPrinter = new ImpexPrinter(writer)
        ) {
            printFieldHeader(impexPrinter);

            impexPrinter.println(ImpexConstant.INSERT_UNTYPED_FIELD_HEADING);

            for (UntypedField untypedField: untypedFields) {
                impexPrinter.printRecord(
                        untypedField.getCode(),
                        untypedField.getName(),
                        untypedField.isMandatory(),
                        untypedField.isActivated(),
                        untypedField.getObjectType(),
                        untypedField.getFieldSetterStrategyBeanName(),
                        untypedField.getDependencies(),
                        untypedField.getUniverses(),
                        untypedField.getGroup(),
                        untypedField.getClassificationAttribute()
                );
            }

            impexPrinter.println(ImpexConstant.INSERT_TYPED_FIELD_HEADING);

            for (TypedField typedField: typedFields) {
                impexPrinter.printRecord(
                        typedField.getCode(),
                        typedField.getName(),
                        typedField.isMandatory(),
                        typedField.isActivated(),
                        typedField.getObjectType(),
                        typedField.getStrategyBeanName(),
                        typedField.getFieldSetterStrategyBeanName(),
                        typedField.getDependencies(),
                        typedField.getUniverses(),
                        typedField.getGroup(),
                        typedField.getClassificationAttribute()
                );
            }

            impexPrinter.flush();
        }
    }

    private void printCategoryHeader(ImpexPrinter impexPrinter) throws IOException {
        printMacroComment(impexPrinter);

        impexPrinter.println();

        impexPrinter.println(ImpexConstant.RPU_PRODUCT_CATALOG);
        impexPrinter.println(ImpexConstant.RPU_CATALOG_VERSION);
        impexPrinter.println(ImpexConstant.LANGUAGE);

        impexPrinter.println();
    }

    private void printCategoryToCategoryHeader(ImpexPrinter impexPrinter) throws IOException {
        printMacroComment(impexPrinter);

        impexPrinter.println();

        impexPrinter.println(ImpexConstant.RPU_PRODUCT_CATALOG);
        impexPrinter.println(ImpexConstant.RPU_CATALOG_VERSION);
        impexPrinter.println(ImpexConstant.SUPER_CATEGORIES);
        impexPrinter.println(ImpexConstant.CATEGORIES);

        impexPrinter.println();
    }

    private void printAttributeAndAttributeValueHeader(ImpexPrinter impexPrinter) throws IOException{
        printMacroComment(impexPrinter);

        impexPrinter.println();

        impexPrinter.println(ImpexConstant.RPU_PRODUCT_CATALOG);
        impexPrinter.println(ImpexConstant.RPU_CLASS_SYSTEM_VERSION);
        impexPrinter.println(ImpexConstant.LANGUAGE);

        impexPrinter.println();
    }

    private void printClassificationHeader(ImpexPrinter impexPrinter) throws IOException{
        printMacroComment(impexPrinter);

        impexPrinter.println();

        impexPrinter.println(ImpexConstant.RPU_CLASSIFICATION_CATALOG);
        impexPrinter.println(ImpexConstant.RPU_CLASS_CATALOG_VERSION);
        impexPrinter.println(ImpexConstant.LANGUAGE);

        impexPrinter.println();
    }

    private void printClassificationAssignementHeader(ImpexPrinter impexPrinter) throws IOException{
        printMacroComment(impexPrinter);

        impexPrinter.println();

        impexPrinter.println(ImpexConstant.RPU_CLASSIFICATION_CATALOG);
        impexPrinter.println(ImpexConstant.RPU_CLASS_CATALOG_VERSION);
        impexPrinter.println(ImpexConstant.RPU_CLASS_SYSTEM_VERSION);
        impexPrinter.println(ImpexConstant.RPU_CLASSIFICATION_CLASS);
        impexPrinter.println(ImpexConstant.RPU_CLASSIFICATION_ATTRIBUTE);

        impexPrinter.println();
    }

    private void printFieldHeader(ImpexPrinter impexPrinter) throws IOException{
        printMacroComment(impexPrinter);

        impexPrinter.println();

        impexPrinter.println("TODO FEIGNASSE");

        impexPrinter.println();
    }


    private void printMacroComment(ImpexPrinter impexPrinter) throws IOException {
        impexPrinter.println(ImpexConstant.MACRO_DEFINITION);
    }
    
    private BufferedWriter initBufferWriter(String fileName) throws IOException {
        final String filePath = folderPath + File.separator + fileName;
        LOG.info("Writing output file in {}", filePath);
        return Files.newBufferedWriter(Paths.get(filePath));
    }
}
