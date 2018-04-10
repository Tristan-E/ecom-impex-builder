package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author teyma
 * @since 09/04/2018
 */
@Getter
@Setter
@EqualsAndHashCode
public class TreeNodeDto {

    public TreeNodeDto() {
        this.children = new HashSet<>();
    }

    private String id;
    private String name;
    private Set<TreeNodeDto> children;
    private TreeNodeType type;
}
