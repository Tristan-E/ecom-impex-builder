package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.ClassificationClass;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 19/03/2018
 */
@Component
public class ClassificationClassMapper {
    public ClassificationClass categoryToClassificationClass(Category category) {
        ClassificationClass classificationClass = new ClassificationClass();
        // TODO
        classificationClass.setCode("TODO");
        classificationClass.setName(category.getName());
        return classificationClass;
    }
}
