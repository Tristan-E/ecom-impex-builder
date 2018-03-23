package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : teyma
 * @since : 18/03/2018
 */
@Getter
@Setter
@EqualsAndHashCode
public class ImpexExporter {
    public ImpexExporter() {
        univers = new HashSet<>();
        families = new HashSet<>();
        subFamilies = new HashSet<>();
        subSubFamilies = new HashSet<>();
        categoryToCategory = new HashSet<>();
        classificationClasses = new HashSet<>();
        classificationAttributes = new HashSet<>();;
        classificationAttributeValues = new HashSet<>();
        classAttributeAssignments = new HashSet<>();
        classificationToCategory = new HashSet<>();

    }

    private Set<PcmCategory> univers;
    private Set<PcmCategory> families;
    private Set<PcmCategory> subFamilies;
    private Set<PcmCategory> subSubFamilies;
    private Set<CategoryCategoryRelation> categoryToCategory;
    private Set<ClassificationClass> classificationClasses;
    private Set<ClassificationAttribute> classificationAttributes;
    private Set<ClassificationAttributeValue> classificationAttributeValues;
    private Set<ClassAttributeAssignment> classAttributeAssignments;
    private Set<CategoryCategoryRelation> classificationToCategory;
}
