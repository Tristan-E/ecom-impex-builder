package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {
}