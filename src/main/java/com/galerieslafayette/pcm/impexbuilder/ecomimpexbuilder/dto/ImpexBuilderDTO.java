package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author teyma
 * @since 14/03/2018
 */
@Getter
@Setter
@EqualsAndHashCode
public class ImpexBuilderDTO {
    @NotNull
    private Long categoryId;
    @NotNull
    private String impexFolder;
}
