package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CategoryDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<CategoryEntity> getAllCategories(){
        try{
            Query query = entityManager.createQuery("select ce from CategoryEntity ce order by ce.category_name");
            return new ArrayList<CategoryEntity>(query.getResultList());
        }catch (NoResultException nre){
            return null;
        }
    }

    public List<CategoryEntity> getAllCategoriesBasedCatId(Integer categoryId){
        try{
            return entityManager.createNamedQuery("getAllCategoriesBasedCatId", CategoryEntity.class).setParameter("categoryId", categoryId).getResultList();
        } catch (NoResultException nre){
            return null;
        }
    }

    public CategoryEntity getCategoryByUUID(String categoryUUID){
        try{
            return entityManager.createNamedQuery("getCategoryByUUID", CategoryEntity.class).setParameter("uuid", categoryUUID).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
    }

}
