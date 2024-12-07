package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.payload.categoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    categoryDTO createCategory(categoryDTO categoryDTO);
    categoryDTO deleteCategory(Long categoriesId);

    categoryDTO updateCategory(Long categoriesID, categoryDTO categorydto);
}
