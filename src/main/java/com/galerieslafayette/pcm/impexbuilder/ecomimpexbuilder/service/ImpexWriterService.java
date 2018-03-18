package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmFamily;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmSubFamily;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmSubSubFamily;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmUnivers;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
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
        writePcmUnivers(impexExporter.getUnivers());
        writePcmFamilies(impexExporter.getFamilies());
        writePcmSubFamilies(impexExporter.getSubFamilies());
        writePcmSubSubFamilies(impexExporter.getSubSubFamilies());
    }

    private void writePcmUnivers(Set<PcmUnivers> univers) throws IOException {
        Path path = Paths.get(folderPath + File.separator + ImpexConstant.PU_FILE_NAME);

        try (
                BufferedWriter writer = Files.newBufferedWriter(path);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator))
        ) {
            printCategoryHeader(csvPrinter);

            for (PcmUnivers u : univers) {
                csvPrinter.printRecord(u.getCode(), u.getName(), u.getExternalCode());
            }
            csvPrinter.flush();
        }
    }

    private void writePcmFamilies(Set<PcmFamily> families) throws IOException {
        Path path = Paths.get(folderPath + File.separator + ImpexConstant.PF_FILE_NAME);

        try (
                BufferedWriter writer = Files.newBufferedWriter(path);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator))
        ) {
            printCategoryHeader(csvPrinter);

            for (PcmFamily f : families) {
                csvPrinter.printRecord(f.getCode(), f.getName(), f.getExternalCode());
            }
            csvPrinter.flush();
        }
    }

    private void writePcmSubFamilies(Set<PcmSubFamily> subFamilies) throws IOException {
        Path path = Paths.get(folderPath + File.separator + ImpexConstant.PSF_FILE_NAME);

        try (
                BufferedWriter writer = Files.newBufferedWriter(path);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator))
        ) {
            printCategoryHeader(csvPrinter);

            for (PcmSubFamily f : subFamilies) {
                csvPrinter.printRecord(f.getCode(), f.getName(), f.getExternalCode());
            }
            csvPrinter.flush();
        }
    }

    private void writePcmSubSubFamilies(Set<PcmSubSubFamily> subSubFamilies) throws IOException {
        Path path = Paths.get(folderPath + File.separator + ImpexConstant.PSSF_FILE_NAME);

        try (
                BufferedWriter writer = Files.newBufferedWriter(path);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator))
        ) {
            printCategoryHeader(csvPrinter);

            for (PcmSubSubFamily f : subSubFamilies) {
                csvPrinter.printRecord(f.getCode(), f.getName(), f.getExternalCode());
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
