package ru.avangard.website.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.avangard.website.entity.Subcategory;
import ru.avangard.website.service.SubcategoryService;
import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
//@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public ResponseEntity<List<Subcategory>> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
        return ResponseEntity.ok(subcategories);
    }

//    @GetMapping("/with-categories")
//    public ResponseEntity<List<Subcategory>> getAllSubcategoriesWithCategories() {
//        List<Subcategory> subcategories = subcategoryService.getAllSubcategoriesWithCategories();
//        return ResponseEntity.ok(subcategories);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Long id) {
        return subcategoryService.getSubcategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/{id}/with-services")
//    public ResponseEntity<Subcategory> getSubcategoryWithServices(@PathVariable Long id) {
//        return subcategoryService.getSubcategoryWithServices(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/search/{alias}")
    public ResponseEntity<Subcategory> getSubcategoryByAlias(@PathVariable String alias) {
        return subcategoryService.getSubcategoryByAlias(alias)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategory(@PathVariable Long categoryId) {
        List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(subcategories);
    }

//    @GetMapping("/category/{categoryId}/ordered")
//    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategoryOrdered(@PathVariable Long categoryId) {
//        List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryIdOrdered(categoryId);
//        return ResponseEntity.ok(subcategories);
//    }

    @PostMapping
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Subcategory subcategory) {
        Subcategory createdSubcategory = subcategoryService.createSubcategory(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id, @RequestBody Subcategory subcategory) {
        if (!subcategoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Subcategory updatedSubcategory = subcategoryService.updateSubcategory(id, subcategory);
        return ResponseEntity.ok(updatedSubcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        if (!subcategoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        subcategoryService.deleteSubcategory(id);
        return ResponseEntity.noContent().build();
    }
}