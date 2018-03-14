package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author teyma
 * @since 11/03/2018
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Category {

    @Id @GeneratedValue
    private Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Category> children;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @ManyToMany
    private Collection<Attribute> attributes;
}
