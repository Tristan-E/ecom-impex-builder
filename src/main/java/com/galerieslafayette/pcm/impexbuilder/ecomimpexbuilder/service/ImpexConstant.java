package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.service;

/**
 * @author : teyma
 * @since : 12/03/2018
 */
public final class ImpexConstant {
    public static final String PU_FILE_NAME = "0_RPU_PU.impex";
    public static final String PF_FILE_NAME = "1_RPU_PF.impex";
    public static final String PSF_FILE_NAME = "2_RPU_PSF.impex";
    public static final String PSSF_FILE_NAME = "3_RPU_PSSF.impex";
    public static final String MAPPING_CATEGORY_FILE_NAME = "4_RPU_MAPPING_CATEGORY.impex";
    public static final String CLASSIFICATION_FILE_NAME = "5_RPU_CLASSIFICATION.impex";
    public static final String ATTRIBUTE_FILE_NAME = "6_RPU_ATTRIBUTE.impex";
    public static final String ATTRIBUTE_VALUE_FILE_NAME = "7_RPU_ATTRIBUTE_VALUE.impex";
    public static final String CLASS_ATTRIBUTE_ASSIGNEMENT_FILE_NAME = "8_RPU_MAPPING_CLA_ATTRIBUTE.impex";
    public static final String MAPPING_CLA_CATEGORY_FILE_NAME = "9_RPU_MAPPING_CLA_CATEGORY.impex";

    public static final String MACRO_DEFINITION = "########################################\n"
                                                + "####       MACRO DEFINITION         ####\n"
                                                + "########################################";

    public static final String RPU_PRODUCT_CATALOG = "$productCatalog=glpcmProductCatalog";
    public static final String RPU_CATALOG_VERSION = "$catalogVersion=catalogversion(catalog(id[default=$productCatalog]), version[default='Staged'])";
    public static final String LANGUAGE = "$lang=fr";
    public static final String SUPER_CATEGORIES = "$supercategories=source(code, $catalogVersion)[unique=true]";
    public static final String CATEGORIES = "$categories=target(code, $catalogVersion)[unique=true]";
    public static final String RPU_CLASSIFICATION_CATALOG = "$classificationCatalog = glpcmClassification";
    public static final String RPU_CLASS_CATALOG_VERSION = "$classCatalogVersion = catalogversion(catalog(id[default = '$classificationCatalog']), version[default = '1.0'])[unique = true, default = '$classificationCatalog:1.0']";
    public static final String RPU_CLASS_SYSTEM_VERSION = "$classSystemVersion = systemVersion(catalog(id[default = '$classificationCatalog']), version[default = '1.0'])[unique = true]";

    public static final String RPU_CLASSIFICATION_CLASS = "$class=classificationClass(ClassificationClass.code,$classCatalogVersion)[unique=true]";
    public static final String RPU_CLASSIFICATION_ATTRIBUTE = "$attribute=classificationAttribute(code,$classSystemVersion)[unique=true]";

    public static final String INSERT_PU_HEADING = "INSERT_UPDATE PcmUnivers;code[unique=true];name[lang=$lang];externalCode[unique=true];$catalogVersion";
    public static final String INSERT_PF_HEADING = "INSERT_UPDATE PcmFamily;code[unique=true];name[lang=$lang];externalCode[unique=true];$catalogVersion";
    public static final String INSERT_PSF_HEADING = "INSERT_UPDATE PcmSubFamily;code[unique=true];name[lang=$lang];externalCode[unique=true];$catalogVersion";
    public static final String INSERT_PSSF_HEADING = "INSERT_UPDATE PcmSubSubFamily;code[unique=true];name[lang=$lang];externalCode[unique=true];$catalogVersion";
    public static final String INSERT_CATEGORY_TO_CATEGORY = "INSERT_UPDATE CategoryCategoryRelation;$categories;$supercategories";
    public static final String INSERT_CLASSIFICATION_CLASS = "INSERT_UPDATE ClassificationClass; $classCatalogVersion; code[unique = true]; name[lang = $lang]; allowedPrincipals(uid)[default = 'customergroup']";
    public static final String INSERT_CLASSIFICATION_ATTRIBUTE = "INSERT_UPDATE ClassificationAttribute; code[unique = true]; name[lang = $lang]; externalId ; $classSystemVersion";
    public static final String INSERT_CLASSIFICATION_ATTRIBUTE_VALUE= "INSERT_UPDATE ClassificationAttributeValue;code[unique=true];name[lang=$lang];$classSystemVersion";
    public static final String INSERT_CLASS_ATTRIBUTE_ASSIGNEMENT = "INSERT_UPDATE ClassAttributeAssignment;$class;$attribute;attributeType(code);mandatory;attributeValues(code,$classSystemVersion)";
    public static final String INSERT_CATEGORY_TO_CLASSIFICATION = "INSERT_UPDATE CategoryCategoryRelation;qualifier;reverseSequenceNumber;sequenceNumber;source(catalogVersion(catalog(id),version),code)[unique=true,allownull=true];target(catalogVersion(catalog(id),version),code)[unique=true,allownull=true]";
}