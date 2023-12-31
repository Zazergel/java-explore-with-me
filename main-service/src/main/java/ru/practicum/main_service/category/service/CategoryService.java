package ru.practicum.main_service.category.service;


import org.springframework.data.domain.Pageable;
import ru.practicum.main_service.category.Category;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(NewCategoryDto newCategoryDto);

    List<CategoryDto> getAll(Pageable pageable);

    CategoryDto getById(Long catId);

    Category getCategoryById(Long catId);

    CategoryDto patch(Long catId, CategoryDto categoryDto);

    void deleteById(Long catId);


}