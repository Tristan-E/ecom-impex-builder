package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmSubFamily;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmUnivers;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class PcmSubFamilyMapper {
    public PcmSubFamily categoryToPcmSubFamily(Category category) {
        PcmSubFamily subFamily = new PcmSubFamily();
        subFamily.setCode("TODO");
        subFamily.setName(category.getName());
        subFamily.setExternalCode("TODO");
        return subFamily;
    }
}
