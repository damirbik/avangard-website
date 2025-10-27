package ru.avangard.website.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.avangard.website.entity.Subcategory;
import ru.avangard.website.repository.ISubcategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

    private final ISubcategoryRepository subcategoryRepository;

    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<Subcategory> getSubcategoryById(Long id) {
        return subcategoryRepository.findById(id);
    }

    public Optional<Subcategory> getSubcategoryByName(String name) {
        return subcategoryRepository.findBySubcategoryName(name);
    }

    public Optional<Subcategory> getSubcategoryWithServices(Long id) {
        return subcategoryRepository.findByIdWithServices(id);
    }

    public List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) {
        return subcategoryRepository.findByCategoryCategoryId(categoryId);
    }

    public List<Subcategory> getSubcategoriesByCategoryIdOrdered(Long categoryId) {
        return subcategoryRepository.findByCategoryIdOrderBySubcategoryId(categoryId);
    }

    public List<Subcategory> getSubcategoriesByCategoryIdOrderedSpringData(Long categoryId) {
        return subcategoryRepository.findByCategoryCategoryIdOrderBySubcategoryId(categoryId);
    }

    public List<Subcategory> getAllSubcategoriesWithCategories() {
        return subcategoryRepository.findAllWithCategory();
    }

    public Subcategory createSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory updateSubcategory(Long id, Subcategory subcategory) {
        subcategory.setSubcategoryId(id);
        return subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long id) {
        subcategoryRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return subcategoryRepository.existsById(id);
    }
}