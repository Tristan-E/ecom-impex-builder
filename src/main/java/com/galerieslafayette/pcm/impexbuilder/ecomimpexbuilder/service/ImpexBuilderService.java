package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDTO;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
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
import java.util.NoSuchElementException;

/**
 * @author : teyma
 * @since : 12/03/2018
 */
@Service
public class ImpexBuilderService {

    private CategoryRepository categoryRepository;

    @Value( "${impex.builder.csv.separator}" )
    private char csvSeparator;

    public ImpexBuilderService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void buildImpex(ImpexBuilderDTO impexBuilderDTO) throws NoSuchElementException, WrongCategoryTypeException, IOException{
        Category puCategory = categoryRepository.findById(impexBuilderDTO.getCategoryId())
                .orElseThrow(NoSuchElementException::new);

        if (CategoryType.PU != puCategory.getType()) {
            throw new WrongCategoryTypeException("Building Impex should be called on Universe (PU), the category found was " + puCategory.getType() + ".");
        }

        Path impexFolderPath  = Paths.get(impexBuilderDTO.getImpexFolder());
        if (!Files.exists(impexFolderPath)) {
            throw new FileNotFoundException("No folder file found with path " + impexFolderPath + ".");
        }

        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(impexBuilderDTO.getImpexFolder() + File.separator + "test.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(csvSeparator).withRecordSeparator(csvSeparator + "\n"))
        ) {
            csvPrinter.printRecord("TEST1", "YO", "YA");
            csvPrinter.printRecord("TEST2", "YO", "YA");
            csvPrinter.printRecord("TEST3", "YO", "YA");

            csvPrinter.flush();
        }

    }
}
