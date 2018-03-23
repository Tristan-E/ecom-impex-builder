package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service.ImpexBuilderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @author teyma
 * @since 14/03/2018
 */
@RestController
@RequestMapping("/impex-builder")
public class ImpexBuilderController {
    private ImpexBuilderService service;

    public ImpexBuilderController(ImpexBuilderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity buildImpex(@RequestBody ImpexBuilderDto impexBuilderDto) {
        try {
            service.buildImpex(impexBuilderDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found.");
        } catch (WrongCategoryTypeException | FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException | RecursionDepthException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while building impex.");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
