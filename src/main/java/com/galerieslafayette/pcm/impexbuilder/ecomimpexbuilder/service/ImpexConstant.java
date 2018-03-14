package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

/**
 * @author : teyma
 * @since : 12/03/2018
 */
public final class ImpexConstant {
    public final String MACRO_DEFINITION =
            "########################################\n" +
            "####       MACRO DEFINITION         ####\n" +
            "########################################";

    public final String RPU_PRODUCT_CATALOG = "$productCatalog=glpcmProductCatalog";
    public final String RPU_CATALOG_VERSION = "$catalogVersion=catalogversion(catalog(id[default=$productCatalog]), version[default='Staged'])";
    public final String LANGUAGE = "$lang=fr";
    public final String SUPER_CATEGORIES = "$supercategories=source(code, $catalogVersion)[unique=true]";
    public final String CATEGORIES = "$categories=target(code, $catalogVersion)[unique=true]";
    public final String RPU_CLASSIFICATION_CATALOG = "$classificationCatalog = glpcmClassification";
    public final String RPU_CLASS_CATALOG_VERSION = "$classCatalogVersion = catalogversion(catalog(id[default = '$classificationCatalog']), version[default = '1.0'])[unique = true, default = '$classificationCatalog:1.0']";
    public final String RPU_CLASS_SYSTEM_VERSION = " $classSystemVersion = systemVersion(catalog(id[default = '$classificationCatalog']), version[default = '1.0'])[unique = true]";

    public final String RPU_CLASSIFICATION_CLASS = "$class=classificationClass(ClassificationClass.code,$classCatalogVersion)[unique=true]";
    public final String RPU_CLASSIFICATION_ATTRIBUTE = "$attribute=classificationAttribute(code,$classSystemVersion)[unique=true]";

    public final String INSERT_PF_HEADING = "INSERT_UPDATE PcmFamily;code[unique=true];name[lang=$lang];$catalogVersion";
    public final String INSERT_PSF_HEADING = "INSERT_UPDATE PcmSubFamily;code[unique=true];name[lang=$lang];externalCode[unique=true];$catalogVersion";
    public final String INSERT_PSSF_HEADING = "INSERT_UPDATE PcmSubSubFamily;code[unique=true];name[lang=$lang];externalCode[unique=true];$catalogVersion";
    public final String INSERT_CATEGORY_TO_CATEGORY = "INSERT_UPDATE CategoryCategoryRelation;$categories;$supercategories";
    public final String INSERT_CLASSIFICATION_CLASS = "INSERT_UPDATE ClassificationClass; $classCatalogVersion; code[unique = true]; name[lang = $lang]; allowedPrincipals(uid)[default = 'customergroup']";
    public final String INSERT_CLASSIFICATION_ATTRIBUTE = "INSERT_UPDATE ClassificationAttribute; code[unique = true]; name[lang = $lang]; externalId ; $classSystemVersion";
    public final String INSERT_CLASSIFICATION_ATTRIBUTE_VALUE_= "INSERT_UPDATE ClassificationAttributeValue;code[unique=true];name[lang=$lang];$classSystemVersion";
    public final String INSERT_CLASS_ATTRIBUTE_ASSIGNEMENT = "INSERT_UPDATE ClassAttributeAssignment;$class;$attribute;attributeType(code);mandatory;attributeValues(code,$classSystemVersion)";
    public final String INSERT_CATEGORY_TO_CLASSIFICATION = "INSERT_UPDATE CategoryCategoryRelation;qualifier;reverseSequenceNumber;sequenceNumber;source(catalogVersion(catalog(id),version),code)[unique=true,allownull=true];target(catalogVersion(catalog(id),version),code)[unique=true,allownull=true]";

}
