package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class AttributeValue {

    @Id
    @GeneratedValue
    private Long id;

    private @NonNull
    String value;

    private String externalId;

    private @NonNull String code;
}
