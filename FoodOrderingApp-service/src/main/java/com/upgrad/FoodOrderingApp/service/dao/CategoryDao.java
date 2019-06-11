package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

}
