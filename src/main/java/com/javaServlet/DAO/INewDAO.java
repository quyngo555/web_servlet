package com.javaServlet.DAO;

import com.javaServlet.Model.CategoryModel;
import com.javaServlet.Model.NewModel;
import com.javaServlet.Paging.Pageble;

import java.util.List;

public interface INewDAO extends  GenericDAO<NewModel> {
    NewModel findOne(Long id);
    List<NewModel> findByCategoryId(Long categoryId);
    Long save(NewModel newModel);
    void update(NewModel updateNew);
    void delete(long id);
    List<NewModel> findAll(Pageble pageble);
    int getTotalItem();
}
