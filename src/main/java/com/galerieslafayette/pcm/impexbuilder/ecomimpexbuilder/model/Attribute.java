package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

/**
 * @author teyma
 * @since 11/03/2018
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Attribute {

    @Id
    @GeneratedValue
    private Long id;

    private @NonNull String name;

    private @NonNull String code;

    @Enumerated(EnumType.STRING)
    private @NonNull AttributeType type;

    @OneToMany
    private Collection<AttributeValue> values;
}
