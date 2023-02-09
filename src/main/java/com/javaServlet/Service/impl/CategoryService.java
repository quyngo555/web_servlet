package com.javaServlet.Service.impl;

import com.javaServlet.DAO.ICategoryDAO;
import com.javaServlet.Model.CategoryModel;
import com.javaServlet.Service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryDAO categoryDao;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDao.findAll();
    }
}
