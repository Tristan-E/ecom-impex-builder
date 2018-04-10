package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.fasterxml.jackson.core.TreeNode;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.TreeNodeDto;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto.TreeNodeType;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmCategory;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util.ImpexUtil;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 09/04/2018
 */
@Component
public class TreeNodeMapper {
    public TreeNodeDto categoryToTreeNodeDto(Category category) {
        if (category == null) {
            return null;
        }

        TreeNodeType treeNodeType = TreeNodeType.CATEGORY;

        TreeNodeDto treeNodeDto = new TreeNodeDto();
        treeNodeDto.setId(treeNodeType.toString() + category.getId());
        treeNodeDto.setType(treeNodeType);
        treeNodeDto.setName(category.getCode() + " - " + category.getName());

        return treeNodeDto;
    }

    public TreeNodeDto attributeToTreeNodeDto(Attribute attribute) {
        if (attribute == null) {
            return null;
        }

        TreeNodeType treeNodeType = TreeNodeType.ATTRIBUTE;

        TreeNodeDto treeNodeDto = new TreeNodeDto();
        treeNodeDto.setId(treeNodeType.toString() + attribute.getId());
        treeNodeDto.setType(treeNodeType);
        treeNodeDto.setName(attribute.getCode() + " - " + attribute.getName());

        return treeNodeDto;
    }

    public TreeNodeDto attributeValueToTreeNodeDto(AttributeValue attributeValue) {
        if (attributeValue == null) {
            return null;
        }

        TreeNodeType treeNodeType = TreeNodeType.CATEGORY;

        TreeNodeDto treeNodeDto = new TreeNodeDto();
        treeNodeDto.setId(treeNodeType.toString() + attributeValue.getId());
        treeNodeDto.setType(treeNodeType);
        treeNodeDto.setName(attributeValue.getCode() + " - " + attributeValue.getValue());

        return treeNodeDto;
    }

}
