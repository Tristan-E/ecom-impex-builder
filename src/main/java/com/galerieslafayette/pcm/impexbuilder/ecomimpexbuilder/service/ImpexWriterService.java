package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
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

        long end = System.currentTimeMillis();
        long time = end - start;

        LOG.info("End exporting impex file in {} ms.", time);
    }

    private void writePcmCategory(Set<PcmCategory> pcmCategories, String insertHeader, String fileName) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(fileName);
                CSVPrinter csvPrinter = initCsvPrinter(writer)
        ) {
            printCategoryHeader(csvPrinter);

            csvPrinter.printRecord(insertHeader);

            for (PcmCategory category : pcmCategories) {
                csvPrinter.printRecord(EMPTY_STRING, category.getCode(), category.getName(), category.getExternalCode());
            }
            csvPrinter.flush();
        }
    }

    private void writeMappingCategories(Set<CategoryCategoryRelation> categoryToCategorySet) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.MAPPING_CATEGORY_FILE_NAME);
                CSVPrinter csvPrinter = initCsvPrinter(writer)
        ) {
            printCategoryToCategoryHeader(csvPrinter);

            csvPrinter.printRecord(ImpexConstant.INSERT_CATEGORY_TO_CATEGORY);

            for (CategoryCategoryRelation categoryToCategory : categoryToCategorySet) {
                csvPrinter.printRecord(EMPTY_STRING, categoryToCategory.getSourceCode(), categoryToCategory.getDestinationCode());
            }
            csvPrinter.flush();
        }
    }

    private void writeClassifications(Set<ClassificationClass> classifications) throws IOException {
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.CLASSIFICATION_FILE_NAME);
                CSVPrinter csvPrinter = initCsvPrinter(writer)
        ) {
            printClassificationHeader(csvPrinter);

            csvPrinter.printRecord(ImpexConstant.INSERT_CLASSIFICATION_CLASS);

            for (ClassificationClass classification : classifications) {
                csvPrinter.printRecord(EMPTY_STRING, EMPTY_STRING, classification.getCode(), classification.getName());
            }
            csvPrinter.flush();
        }
    }

    private void writeAttributes(Set<ClassificationAttribute> attributes) throws IOException{
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.ATTRIBUTE_FILE_NAME);
                CSVPrinter csvPrinter = initCsvPrinter(writer)
        ) {
            printCategoryToCategoryHeader(csvPrinter);

            csvPrinter.printRecord(ImpexConstant.INSERT_CLASSIFICATION_ATTRIBUTE);

            for (ClassificationAttribute attribute : attributes) {
                csvPrinter.printRecord(EMPTY_STRING, attribute.getCode(), attribute.getName(), attribute.getExternalId());
            }
            csvPrinter.flush();
        }
    }

    private void writeAttributeValues(Set<ClassificationAttributeValue> attributeValues) throws IOException{
        try (
                BufferedWriter writer = initBufferWriter(ImpexConstant.ATTRIBUTE_VALUE_FILE_NAME);
                CSVPrinter csvPrinter = initCsvPrinter(writer)
        ) {
            printAttributeAndAttributeValueHeader(csvPrinter);

            csvPrinter.printRecord(ImpexConstant.INSERT_CLASSIFICATION_ATTRIBUTE_VALUE);

            for (ClassificationAttributeValue attributeValue : attributeValues) {
                csvPrinter.printRecord(EMPTY_STRING, attributeValue.getCode(), attributeValue.getValue());
            }
            csvPrinter.flush();
        }
    }

    private void printCategoryHeader(CSVPrinter csvPrinter) throws IOException {
        printMacroComment(csvPrinter);

        csvPrinter.println();

        csvPrinter.printRecord(ImpexConstant.RPU_PRODUCT_CATALOG);
        csvPrinter.printRecord(ImpexConstant.RPU_CATALOG_VERSION);
        csvPrinter.printRecord(ImpexConstant.LANGUAGE);

        csvPrinter.println();
    }

    private void printCategoryToCategoryHeader(CSVPrinter csvPrinter) throws IOException {
        printMacroComment(csvPrinter);

        csvPrinter.println();

        csvPrinter.printRecord(ImpexConstant.RPU_PRODUCT_CATALOG);
        csvPrinter.printRecord(ImpexConstant.RPU_CATALOG_VERSION);
        csvPrinter.printRecord(ImpexConstant.SUPER_CATEGORIES);
        csvPrinter.printRecord(ImpexConstant.CATEGORIES);

        csvPrinter.println();
    }

    private void printAttributeAndAttributeValueHeader(CSVPrinter csvPrinter) throws IOException{
        printMacroComment(csvPrinter);

        csvPrinter.println();

        csvPrinter.printRecord(ImpexConstant.RPU_PRODUCT_CATALOG);
        csvPrinter.printRecord(ImpexConstant.RPU_CLASS_SYSTEM_VERSION);
        csvPrinter.printRecord(ImpexConstant.LANGUAGE);

        csvPrinter.println();
    }

    private void printClassificationHeader(CSVPrinter csvPrinter) throws IOException{
        printMacroComment(csvPrinter);

        csvPrinter.println();

        csvPrinter.printRecord(ImpexConstant.RPU_CLASSIFICATION_CATALOG);
        csvPrinter.printRecord(ImpexConstant.RPU_CLASS_CATALOG_VERSION);
        csvPrinter.printRecord(ImpexConstant.LANGUAGE);

        csvPrinter.println();
    }

    private void printMacroComment(CSVPrinter csvPrinter) throws IOException {
        csvPrinter.printRecord(ImpexConstant.MACRO_DEFINITION_1);
        csvPrinter.printRecord(ImpexConstant.MACRO_DEFINITION_2);
        csvPrinter.printRecord(ImpexConstant.MACRO_DEFINITION_3);
    }

    private CSVPrinter initCsvPrinter(BufferedWriter writer) throws IOException {
        return new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator).withEscape('\\').withQuoteMode(QuoteMode.NONE));
    }

    private BufferedWriter initBufferWriter(String fileName) throws IOException {
        final String filePath = folderPath + File.separator + fileName;
        LOG.info("Writing output file in {}", filePath);
        return Files.newBufferedWriter(Paths.get(filePath));
    }
}
