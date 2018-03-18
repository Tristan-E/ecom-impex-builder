package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDTO;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.springframework.stereotype.Service;

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
    private ImpexExporterService impexExporterService;
    private ImpexWriterService impexWriterService;

    public ImpexBuilderService(CategoryRepository categoryRepository, ImpexExporterService impexExporterService, ImpexWriterService impexWriterService) {
        this.categoryRepository = categoryRepository;
        this.impexExporterService = impexExporterService;
        this.impexWriterService = impexWriterService;
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

        ImpexExporter impexExporter = impexExporterService.buildImpexExporter(puCategory);
        impexWriterService.write(impexExporter);
    }
}
