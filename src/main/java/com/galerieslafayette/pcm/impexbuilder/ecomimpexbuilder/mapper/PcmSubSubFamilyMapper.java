package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmSubSubFamily;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmUnivers;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class PcmSubSubFamilyMapper {
    public PcmSubSubFamily categoryToPcmSubSubFamily(Category category) {
        PcmSubSubFamily subSubFamily = new PcmSubSubFamily();
        subSubFamily.setCode("TODO");
        subSubFamily.setName(category.getName());
        subSubFamily.setExternalCode("TODO");
        return subSubFamily;
    }
}
