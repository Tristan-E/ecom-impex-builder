package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.AttributeRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Attribute;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author teyma
 * @since 03/04/2018
 */
@RestController
@RequestMapping("/attributes")
public class AttributeController {
    private AttributeRepository repository;

    public AttributeController(AttributeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<Attribute> getAttributes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Attribute getAttribute(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public void addAttribute(@RequestBody Attribute category) {
        repository.save(category);
    }

    @PutMapping
    public void updateAttribute(@RequestBody Attribute category) {
        repository.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteAttribute(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
