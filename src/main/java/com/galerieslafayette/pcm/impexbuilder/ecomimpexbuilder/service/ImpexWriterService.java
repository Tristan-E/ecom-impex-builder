package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmCategory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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

    @Value( "${impex.builder.csv.separator}" )
    private char csvSeparator;

    @Value( "${impex.builder.folderpath}" )
    private String folderPath;

    public void write(ImpexExporter impexExporter) throws IOException {
        writePcmCategory(impexExporter.getUnivers(), ImpexConstant.PU_FILE_NAME);
        writePcmCategory(impexExporter.getFamilies(), ImpexConstant.PF_FILE_NAME);
        writePcmCategory(impexExporter.getSubFamilies(), ImpexConstant.PSF_FILE_NAME);
        writePcmCategory(impexExporter.getSubSubFamilies(), ImpexConstant.PSSF_FILE_NAME);
    }

    private void writePcmCategory(Set<PcmCategory> pcmCategories, String fileName) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(folderPath + File.separator + fileName));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator))
        ) {
            printCategoryHeader(csvPrinter);

            for (PcmCategory category : pcmCategories) {
                csvPrinter.printRecord(category.getCode(), category.getName(), category.getExternalCode());
            }
            csvPrinter.flush();
        }
    }

    private void printCategoryHeader(CSVPrinter csvPrinter) throws IOException {
        csvPrinter.printRecord(ImpexConstant.MACRO_DEFINITION_1);
        csvPrinter.printRecord(ImpexConstant.MACRO_DEFINITION_2);
        csvPrinter.printRecord(ImpexConstant.MACRO_DEFINITION_3);

        csvPrinter.println();

        csvPrinter.printRecord(ImpexConstant.RPU_PRODUCT_CATALOG);
        csvPrinter.printRecord(ImpexConstant.RPU_CATALOG_VERSION);
        csvPrinter.printRecord(ImpexConstant.LANGUAGE);

        csvPrinter.println();
    }
}
