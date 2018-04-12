package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;
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

    private String externalId;

    @Enumerated(EnumType.STRING)
    private @NonNull AttributeType type;

    @ManyToMany
    private Set<AttributeValue> values;
}
