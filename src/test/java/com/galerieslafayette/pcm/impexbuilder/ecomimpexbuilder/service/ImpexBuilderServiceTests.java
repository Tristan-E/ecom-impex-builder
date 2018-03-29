package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.PcmCategoryMapper;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class ImpexBuilderServiceTests {

    @InjectMocks
    private ImpexBuilderService impexBuilderService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ImpexExporterService impexExporterService;
    @Mock
    private ImpexWriterService impexWriterService;

    @Test(expected = NoSuchElementException.class)
    public void testBuildImpexShouldThrowNoSuchElementException() throws NoSuchElementException, WrongCategoryTypeException, IOException, RecursionDepthException {
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        impexBuilderService.buildImpex(new ImpexBuilderDto());
    }

    @Test(expected = WrongCategoryTypeException.class)
    public void testBuildImpexShouldThrowWrongCategoryTypeException() throws NoSuchElementException, WrongCategoryTypeException, IOException, RecursionDepthException {
        Category wrongCategory = new Category();
        wrongCategory.setType(CategoryType.PF);

        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(wrongCategory));
        impexBuilderService.buildImpex(new ImpexBuilderDto());
    }

    @Test(expected = RecursionDepthException.class)
    public void testBuildImpexShouldThrowRecursionDepthException() throws RecursionDepthException, IOException, WrongCategoryTypeException {
        Category category = puCategory();

        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category));

        Mockito.doThrow(RecursionDepthException.class).when(impexExporterService).buildImpexExporter(Mockito.any(), Mockito.anyInt());

        impexBuilderService.buildImpex(new ImpexBuilderDto());
    }

    @Test(expected = IOException.class)
    public void testBuildImpexShouldThrowIOException() throws RecursionDepthException, IOException, WrongCategoryTypeException {
        Category category = puCategory();

        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category));

        Mockito.when(impexExporterService.buildImpexExporter(Mockito.any(), Mockito.anyInt())).thenReturn(new ImpexExporter());

        Mockito.doThrow(IOException.class).when(impexWriterService).write(Mockito.any());

        impexBuilderService.buildImpex(new ImpexBuilderDto());

    }

    @Test
    public void testBuildImpexIsOk() throws NoSuchElementException, WrongCategoryTypeException, IOException, RecursionDepthException {
        Category category = puCategory();

        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category));

        ImpexExporter impexExporter = Mockito.mock(ImpexExporter.class);

        Mockito.when(impexExporterService.buildImpexExporter(Mockito.any(), Mockito.anyInt())).thenReturn(impexExporter);

        ImpexBuilderDto impexBuilderDto = new ImpexBuilderDto();
        impexBuilderDto.setCategoryId(111L);
        impexBuilderDto.setClassificationStartNumber(25);

        impexBuilderService.buildImpex(impexBuilderDto);

        Mockito.verify(categoryRepository).findById(impexBuilderDto.getCategoryId());

        Mockito.verify(impexExporterService).buildImpexExporter(category, impexBuilderDto.getClassificationStartNumber());

        Mockito.verify(impexWriterService).write(impexExporter);
    }

    private Category puCategory() {
        Category puCategory = new Category();
        puCategory.setType(CategoryType.PU);
        return puCategory;
    }
}
