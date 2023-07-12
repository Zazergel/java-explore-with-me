package ru.practicum.main_service.category;

import org.mapstruct.Mapper;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.dto.NewCategoryDto;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto);

    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}