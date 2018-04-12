package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author teyma
 * @since 09/04/2018
 */
@Getter
@Setter
@EqualsAndHashCode
public class TreeNodeDto {

    public TreeNodeDto() {
        this.children = new TreeSet<>(Comparator.comparing(TreeNodeDto::getType).thenComparing(TreeNodeDto::getName));
    }

    private String id;
    private String name;
    private SortedSet<TreeNodeDto> children;
    private TreeNodeType type;
}
