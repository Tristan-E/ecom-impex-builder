package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmCategory;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class PcmCategoryMapper {
    public PcmCategory categoryToPcmCategory(Category category) {
        PcmCategory pcmCategory = new PcmCategory();
        pcmCategory.setCode(category.getCode());
        pcmCategory.setName(category.getName());
        pcmCategory.setExternalCode("TODO");
        return pcmCategory;
    }
}
