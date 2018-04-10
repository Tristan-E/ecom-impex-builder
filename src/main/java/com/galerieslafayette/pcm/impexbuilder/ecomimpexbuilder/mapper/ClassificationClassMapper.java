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
    public ClassificationClass categoryToClassificationClass(Category category, String classificationCode) {
        if (category == null) {
            return null;
        }

        ClassificationClass classificationClass = new ClassificationClass();
        classificationClass.setCode(classificationCode);
        classificationClass.setName(category.getName());
        return classificationClass;
    }
}
