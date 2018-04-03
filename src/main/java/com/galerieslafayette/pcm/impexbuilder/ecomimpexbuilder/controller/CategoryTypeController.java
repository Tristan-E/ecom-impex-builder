package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author teyma
 * @since 02/04/18.
 */
@RestController
@RequestMapping("/category-types")
public class CategoryTypeController extends EnumControllerTemplate<CategoryType> {

    public CategoryTypeController() {
        super(CategoryType.class);
    }
}
