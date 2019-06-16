package com.upgrad.FoodOrderingApp.service.business;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryBusinessService {

    @Autowired
    CategoryDao categoryDao;

    public List<CategoryEntity> getAllCategories(){
        List<CategoryEntity> allCategories = categoryDao.getAllCategories();

        return allCategories;
    }

    public List<CategoryEntity> getAllCategoriesBasedCatId(Integer categoryId){
        List<CategoryEntity> allCategoriesBasedCatId = categoryDao.getAllCategoriesBasedCatId(categoryId);

        return allCategoriesBasedCatId;
    }

    public CategoryEntity getCategoryByUUID(String categoryUUID) throws CategoryNotFoundException {
        if(categoryUUID.equals("")){
            throw new CategoryNotFoundException("CNF-001)", "Category id field should not be empty");
        }
        CategoryEntity categoryByUUID = categoryDao.getCategoryByUUID(categoryUUID);
        if(categoryByUUID == null){
            throw new CategoryNotFoundException("CNF-002","No category by this id");
        }
        return categoryByUUID;
    }

    public List<CategoryEntity> getAllCategoriesByRestId(Integer restuarantId) {
        List<CategoryEntity> allCategoriesByRestId = categoryDao.getAllCategoriesByRestId(restuarantId);
        return allCategoriesByRestId;
    }

    public List<ItemEntity> getAllCategoryItems(Integer categoryId) {
        List<ItemEntity> allCategoryItems = categoryDao.getAllCategoryItems(categoryId);
        return allCategoryItems;
    }
}
