package com.javaServlet.Service;

import com.javaServlet.Model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
}
