package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmUnivers;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class PcmUniversMapper {
    public PcmUnivers categoryToPcmUnivers(Category category) {
        PcmUnivers univers = new PcmUnivers();
        univers.setCode("TODO");
        univers.setName(category.getName());
        univers.setExternalCode("TODO");
        return univers;
    }
}
