package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CategoryRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTests {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testGetCategoriesShouldFindAll() {
        categoryController.getCategories();
        Mockito.verify(categoryRepository).findAll();
    }

    @Test
    public void testGetCategoryShouldFindOne() {
        Long testId = 13L;
        categoryController.getCategory(testId);

        Mockito.verify(categoryRepository).findById(testId);
    }

    @Test
    public void testAddCategoryShouldSave() {
        Category category = Mockito.mock(Category.class);
        categoryController.addCategory(category);

        Mockito.verify(categoryRepository).save(category);
    }

    @Test
    public void testUpdateCategoryShouldSave() {
        Category category = Mockito.mock(Category.class);
        categoryController.updateCategory(category);

        Mockito.verify(categoryRepository).save(category);
    }

    @Test
    public void deleteCategory() {
        Long testId = 132L;
        categoryController.deleteCategory(testId);

        Mockito.verify(categoryRepository).deleteById(testId);
    }
}
