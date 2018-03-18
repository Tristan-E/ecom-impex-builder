package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service.ImpexBuilderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/{categoryId}")
    public ResponseEntity buildImpex(Long categoryId) {
        try {
            service.buildImpex(categoryId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found.");
        } catch (WrongCategoryTypeException | FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while building impex.");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
