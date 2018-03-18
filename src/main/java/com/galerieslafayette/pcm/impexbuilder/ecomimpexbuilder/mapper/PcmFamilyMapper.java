package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmFamily;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmUnivers;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class PcmFamilyMapper {
    public PcmFamily categoryToPcmFamily(Category category) {
        PcmFamily family = new PcmFamily();
        family.setCode("TODO");
        family.setName(category.getName());
        family.setExternalCode("TODO");
        return family;
    }
}
