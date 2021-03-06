package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * @author teyma
 * @since 11/03/2018
 */
@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Collection<Category> findByType(CategoryType categoryType);
}