package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.exceptions.APIExceptions;
import com.ecommerce.sb_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.payload.CategoryResponse;
import com.ecommerce.sb_ecom.payload.categoryDTO;
import com.ecommerce.sb_ecom.repository.CategoryRepository;
import com.ecommerce.sb_ecom.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{



    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortBy, String sortOrder) {


        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if(categories.isEmpty())
            throw new APIExceptions("No category created till now ");

        List<categoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, categoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalpages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public categoryDTO createCategory(categoryDTO CategoryDTO) {
        Category category = modelMapper.map(CategoryDTO,Category.class);
        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb!=null)
            throw new APIExceptions("Category with the name " + category.getCategoryName()+ " already exists !!!");
    Category savedCategory = categoryRepository.save(category);
    categoryDTO savedCategoryDTO = modelMapper.map(savedCategory, categoryDTO.class);
    return savedCategoryDTO;
    }

    @Override
    public categoryDTO deleteCategory(Long categoriesId) {

        Category category = categoryRepository.findById(categoriesId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoriesId));

        categoryRepository.delete(category);
        return modelMapper.map(category, categoryDTO.class);
    }

    @Override
    public categoryDTO updateCategory(Long categoriesID, categoryDTO categorydto) {

        Category category = modelMapper.map(categorydto,Category.class);
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoriesID);

        Category savedCategory = savedCategoryOptional
                .orElseThrow( () -> new  ResourceNotFoundException("Category","categoryId",categoriesID));

        category.setCategoryId(categoriesID);

        savedCategory = categoryRepository.save(category);
         categoryDTO savedCategoryDTO = modelMapper.map(savedCategory, categoryDTO.class);
        return savedCategoryDTO;

    }
}
