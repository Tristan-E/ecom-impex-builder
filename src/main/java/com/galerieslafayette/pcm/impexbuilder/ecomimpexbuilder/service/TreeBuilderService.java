package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.ImpexBuilderDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.TreeNodeDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.RecursionDepthException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.exception.WrongCategoryTypeException;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.ImpexExporter;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.CategoryCategoryRelation;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper.TreeNodeMapper;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author : teyma
 * @since : 09/04/2018
 */
@Service
public class TreeBuilderService {

    private static final int MAX_DEPTH = 4;

    private CategoryRepository categoryRepository;
    private TreeNodeMapper treeNodeMapper;

    public TreeBuilderService(CategoryRepository categoryRepository, TreeNodeMapper treeNodeMapper) {
        this.categoryRepository = categoryRepository;
        this.treeNodeMapper = treeNodeMapper;
    }

    public TreeNodeDto getTreeByCategory(Long categoryId) throws NoSuchElementException, WrongCategoryTypeException, RecursionDepthException {
        Category puCategory = categoryRepository.findById(categoryId).orElseThrow(NoSuchElementException::new);

        if (CategoryType.PU != puCategory.getType()) {
            throw new WrongCategoryTypeException("Building tree should be called on Universe (PU), the category found was " + puCategory.getType() + ".");
        }

        TreeNodeDto mainNode = treeNodeMapper.categoryToTreeNodeDto(puCategory);
        mainNode.getChildren().addAll(getAttributeNodes(puCategory.getAttributes()));

        handleCategories(puCategory, null, mainNode, 1);
        return mainNode;
    }

    private void handleCategories(Category category, TreeNodeDto parentNode, TreeNodeDto mainNode, int depth) throws RecursionDepthException {
        if (depth > MAX_DEPTH) {
            throw new RecursionDepthException("Categories should only be on 4 levels (PU,PF,PSF,PSSF).");
        }

        TreeNodeDto node = treeNodeMapper.categoryToTreeNodeDto(category);
        node.getChildren().addAll(getAttributeNodes(category.getAttributes()));

        if (parentNode != null) {
            parentNode.getChildren().add(node);
        }

        for (Category childCategory : category.getChildren()) {
            if (depth == 1) {
                handleCategories(childCategory, mainNode, null, depth + 1);
            } else {
                handleCategories(childCategory, node, null, depth + 1);
            }
        }
    }

    private Set<TreeNodeDto> getAttributeNodes(Set<Attribute> attributes) {
        Set<TreeNodeDto> treeNodeDtos = new HashSet<>();
        TreeNodeDto attributeNode;
        for (Attribute attribute: attributes) {
            attributeNode = treeNodeMapper.attributeToTreeNodeDto(attribute);
            for (AttributeValue attributeValue: attribute.getValues()) {
                attributeNode.getChildren().add(treeNodeMapper.attributeValueToTreeNodeDto(attributeValue));
            }
            treeNodeDtos.add(attributeNode);
        }
        return treeNodeDtos;
    }
}
