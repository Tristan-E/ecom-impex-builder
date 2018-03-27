package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.AttributeValueRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service.ImpexBuilderService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @author teyma
 * @since 27/03/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImpexBuilderControllerTests {

    @InjectMocks
    private ImpexBuilderController impexBuilderController;

    @Mock
    private ImpexBuilderService impexBuilderService;

    private final ImpexBuilderDto impexBuilderDto = Mockito.mock(ImpexBuilderDto.class);

    @Test
    public void testBuildImpexShouldReturnNotFound() throws Exception {
        testBuildImpexWithException(NoSuchElementException.class, HttpStatus.NOT_FOUND);
    }

    @Test
    public void testBuildImpexShouldReturnBadRequest_FileNotFound() throws Exception{
        testBuildImpexWithException(FileNotFoundException.class, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testBuildImpexShouldReturnBadRequest_WrongCategory() throws Exception{
        testBuildImpexWithException(WrongCategoryTypeException.class, HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testBuildImpexShouldReturnInternalServerError_IOException() throws Exception{
        testBuildImpexWithException(IOException.class, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testBuildImpexShouldReturnInternalServerError_RecursionDepth() throws Exception{
        testBuildImpexWithException(RecursionDepthException.class, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testBuildImpexOk() throws Exception{
        ResponseEntity response = impexBuilderController.buildImpex(impexBuilderDto);
        Mockito.verify(impexBuilderService).buildImpex(impexBuilderDto);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void testBuildImpexWithException(Class exceptionClass, HttpStatus expectedStatus) throws Exception{
        Mockito.doThrow(exceptionClass).when(impexBuilderService).buildImpex(impexBuilderDto);

        ResponseEntity response = impexBuilderController.buildImpex(impexBuilderDto);
        Mockito.verify(impexBuilderService).buildImpex(impexBuilderDto);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(expectedStatus);
    }
}
