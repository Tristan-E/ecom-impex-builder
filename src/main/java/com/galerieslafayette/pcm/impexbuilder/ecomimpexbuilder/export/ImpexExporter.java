package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.export.model.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.TypeFactory;

import java.util.*;

/**
 * @author : teyma
 * @since : 18/03/2018
 */
@Getter
@Setter
@EqualsAndHashCode
public class ImpexExporter {
    public ImpexExporter() {
        univers = new TreeSet<>(Comparator.comparing(PcmCategory::getCode));
        families = new TreeSet<>(Comparator.comparing(PcmCategory::getCode));
        subFamilies = new TreeSet<>(Comparator.comparing(PcmCategory::getCode));
        subSubFamilies = new TreeSet<>(Comparator.comparing(PcmCategory::getCode));
        categoryToCategory = new TreeSet<>(Comparator.comparing(CategoryCategoryRelation::getSourceCode).thenComparing(CategoryCategoryRelation::getDestinationCode));
        classificationClasses = new TreeSet<>(Comparator.comparing(ClassificationClass::getCode));
        classificationAttributes = new TreeSet<>(Comparator.comparing(ClassificationAttribute::getCode));;
        classificationAttributeValues = new TreeSet<>(Comparator.comparing(ClassificationAttributeValue::getCode));
        classAttributeAssignments = new TreeSet<>(Comparator.comparing(ClassAttributeAssignment::getClassCode).thenComparing(Comparator.comparing(ClassAttributeAssignment::getAttributeCode)));
        classificationToCategory = new TreeSet<>(Comparator.comparing(CategoryCategoryRelation::getSourceCode).thenComparing(CategoryCategoryRelation::getDestinationCode));
        classificationToClassification = new TreeSet<>(Comparator.comparing(CategoryCategoryRelation::getSourceCode).thenComparing(CategoryCategoryRelation::getDestinationCode));
        untypedFields = new TreeSet<>(Comparator.comparing(UntypedField::getCode));
        typedFields = new TreeSet<>(Comparator.comparing(TypedField::getCode));
    }

    private SortedSet<PcmCategory> univers;
    private SortedSet<PcmCategory> families;
    private SortedSet<PcmCategory> subFamilies;
    private SortedSet<PcmCategory> subSubFamilies;
    private SortedSet<CategoryCategoryRelation> categoryToCategory;
    private SortedSet<ClassificationClass> classificationClasses;
    private SortedSet<ClassificationAttribute> classificationAttributes;
    private SortedSet<ClassificationAttributeValue> classificationAttributeValues;
    private SortedSet<ClassAttributeAssignment> classAttributeAssignments;
    private SortedSet<CategoryCategoryRelation> classificationToCategory;
    private SortedSet<CategoryCategoryRelation> classificationToClassification;
    private SortedSet<UntypedField> untypedFields;
    private SortedSet<TypedField> typedFields;
}
