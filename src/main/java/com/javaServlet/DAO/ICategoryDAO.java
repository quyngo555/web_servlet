package com.javaServlet.DAO;

import com.javaServlet.Model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends  GenericDAO<CategoryModel>{
    List<CategoryModel> findAll();
    CategoryModel findOne(long id);
    CategoryModel findOneByCode(String code);

}
