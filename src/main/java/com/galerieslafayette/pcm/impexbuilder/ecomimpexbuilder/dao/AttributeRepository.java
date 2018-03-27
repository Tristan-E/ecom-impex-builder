package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author teyma
 * @since 21/03/2018
 */
@RepositoryRestResource
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}