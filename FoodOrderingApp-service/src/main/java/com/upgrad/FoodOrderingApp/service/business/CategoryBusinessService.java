package com.upgrad.FoodOrderingApp.service.business;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBusinessService {

    @Autowired
    CategoryDao categoryDao;

    public List<CategoryEntity> getAllCategories(){
        List<CategoryEntity> allCategories = categoryDao.getAllCategories();

        return allCategories;
    }
}
