package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author teyma
 * @since 11/03/2018
 */
@Entity
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Category {

    public Category () {
        children = new HashSet<>();
        attributes = new HashSet<>();
    }

    @Id @GeneratedValue
    private Long id;

    private String code;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Category> children;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @ManyToMany
    private Set<Attribute> attributes;
}
