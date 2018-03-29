package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.mapper;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.PcmCategory;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.util.ImpexUtil;
import org.springframework.stereotype.Component;

/**
 * @author teyma
 * @since 18/03/2018
 */
@Component
public class PcmCategoryMapper {
    public PcmCategory categoryToPcmCategory(Category category) {
        if (category == null) {
            return null;
        }
        PcmCategory pcmCategory = new PcmCategory();
        pcmCategory.setCode(category.getCode());
        pcmCategory.setName(category.getName());
        pcmCategory.setExternalCode(keepNumericAndUnderscore(category.getCode()));
        return pcmCategory;
    }

    /**
     * Protected to be mocked
     * @param attributeCode
     * @return
     */
    protected String keepNumericAndUnderscore(String attributeCode) {
        return ImpexUtil.keepNumericAndUnderscore(attributeCode);
    }

}
