package red.social.interesescomunes.category.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.category.application.input.ICategoryServicePort;
import red.social.interesescomunes.category.domain.model.Category;
import red.social.interesescomunes.category.infrastructure.input.api.dto.request.CategoryRequest;
import red.social.interesescomunes.category.infrastructure.input.api.dto.response.CategoryResponse;
import red.social.interesescomunes.category.infrastructure.input.api.mapper.ICategoryRestMapper;
import java.util.List;


/**
 * Controlador responsable de procesar las peticiones del cliente relacionadas con la entidad categora.
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final ICategoryServicePort service;
    private final ICategoryRestMapper mapper;

    public CategoryController(ICategoryServicePort service, ICategoryRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<CategoryResponse>> findAllCategories() {
        List<Category> categories = service.findAllCategories();
        List<CategoryResponse> responses = mapper.toCategoryResponseList(categories);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id) {
        Category category = service.findCategoryById(id);
        CategoryResponse response = mapper.toCategoryResponse(category);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<CategoryResponse> findCategoryByName(@RequestParam String name) {
        Category category = service.findCategoryByName(name);
        CategoryResponse response = mapper.toCategoryResponse(category);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = mapper.toDomain(categoryRequest);
        Category savedCategory = service.createCategory(category);
        CategoryResponse response = mapper.toCategoryResponse(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = mapper.toDomain(categoryRequest);
        Category updatedCategory = service.updateCategory(id, category);
        CategoryResponse response = mapper.toCategoryResponse(updatedCategory);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Categoría eliminada.");
    }
}