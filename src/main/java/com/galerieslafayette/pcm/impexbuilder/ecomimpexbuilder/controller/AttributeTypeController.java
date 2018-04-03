package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeType;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author teyma
 * @since 02/04/18.
 */
@RestController
@RequestMapping("/attribute-types")
public class AttributeTypeController extends EnumControllerTemplate<AttributeType> {

    public AttributeTypeController() {
        super(AttributeType.class);
    }
}
