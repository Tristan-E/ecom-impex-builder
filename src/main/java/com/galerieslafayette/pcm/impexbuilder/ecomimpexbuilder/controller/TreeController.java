package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.TreeNodeDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service.TreeBuilderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author teyma
 * @since 09/04/2018
 */
@RestController
@RequestMapping("/tree")
public class TreeController {
    private TreeBuilderService treeBuilderService;

    public TreeController(TreeBuilderService treeBuilderService) {
        this.treeBuilderService = treeBuilderService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getTreeByCategory(@PathVariable Long categoryId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(treeBuilderService.getTreeByCategory(categoryId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found.");
        } catch (WrongCategoryTypeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RecursionDepthException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while building impex.");
        }
    }
}
