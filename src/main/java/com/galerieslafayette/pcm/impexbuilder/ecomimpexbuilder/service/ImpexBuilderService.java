package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public void buildImpex(Long categoryId) throws NoSuchElementException, WrongCategoryTypeException, IOException, RecursionDepthException{
        Category puCategory = categoryRepository.findById(categoryId).orElseThrow(NoSuchElementException::new);

        if (CategoryType.PU != puCategory.getType()) {
            throw new WrongCategoryTypeException("Building Impex should be called on Universe (PU), the category found was " + puCategory.getType() + ".");
        }

        ImpexExporter impexExporter = impexExporterService.buildImpexExporter(puCategory);
        impexWriterService.write(impexExporter);
    }
}
