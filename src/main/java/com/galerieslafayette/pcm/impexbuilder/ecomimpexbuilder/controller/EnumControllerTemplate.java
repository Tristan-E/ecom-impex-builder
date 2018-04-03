package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author teyma
 * @since 02/04/18.
 */
public abstract class EnumControllerTemplate<T extends Enum> {

    private Class<T> clazz;

    public EnumControllerTemplate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @GetMapping
    public List<T> getEnumList() {
        return Arrays.asList(clazz.getEnumConstants());
    }
}

