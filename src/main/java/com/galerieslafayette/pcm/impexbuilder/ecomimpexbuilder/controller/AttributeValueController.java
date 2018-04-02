package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.AttributeValueRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.AttributeValue;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author teyma
 * @since 21/03/2018
 */
@RestController
@RequestMapping("/attribute-values")
public class AttributeValueController {
    private AttributeValueRepository repository;

    public AttributeValueController(AttributeValueRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<AttributeValue> getAttributeValues() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public AttributeValue getAttributeValue(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public void addAttributeValue(@RequestBody AttributeValue attributeValue) {
        repository.save(attributeValue);
    }

    @PutMapping
    public void updateAttributeValue(@RequestBody AttributeValue attributeValue) {
        repository.save(attributeValue);
    }

    @DeleteMapping("/{id}")
    public void deleteAttributeValue(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
