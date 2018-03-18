package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author teyma
 * @since 11/03/2018
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Attribute {

    public Attribute() {
        this.values = new HashSet<>();
    }

    @Id
    @GeneratedValue
    private Long id;

    private @NonNull String name;

    private @NonNull String code;

    @Enumerated(EnumType.STRING)
    private @NonNull AttributeType type;

    @OneToMany
    private Set<AttributeValue> values;
}
