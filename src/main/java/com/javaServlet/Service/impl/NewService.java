package com.javaServlet.Service.impl;

import com.javaServlet.DAO.ICategoryDAO;
import com.javaServlet.DAO.INewDAO;
import com.javaServlet.Model.CategoryModel;
import com.javaServlet.Model.NewModel;
import com.javaServlet.Paging.Pageble;
import com.javaServlet.Service.INewService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewService implements INewService {
    @Inject
    private INewDAO newDao;

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<NewModel> findByCategoryId(Long categoryId) {
        return newDao.findByCategoryId(categoryId);
    }

    @Override
    public NewModel save(NewModel newModel) {
        newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        String a = newModel.getCategoryCode();
        CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
        newModel.setCategoryId(category.getId());
        Long newId = newDao.save(newModel);
        return newDao.findOne(newId);
    }

    @Override
    public NewModel update(NewModel updateNew) {
        NewModel oldNew = newDao.findOne(updateNew.getId());
        updateNew.setCreatedDate(oldNew.getCreatedDate());
        updateNew.setCreatedBy(oldNew.getCreatedBy());
        updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        CategoryModel category = categoryDAO.findOneByCode(updateNew.getCategoryCode());
        updateNew.setCategoryId(category.getId());
        newDao.update(updateNew);
        return newDao.findOne(updateNew.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (long id: ids) {
            //1.delete comment (khoa ngoai new_id)
            //2.delete news
            newDao.delete(id);
        }
    }

    @Override
    public List<NewModel> findAll(Pageble pageble) {
        return newDao.findAll(pageble);
    }

    @Override
    public int getTotalItem() {
        return newDao.getTotalItem();
    }

    @Override
    public NewModel findOne(long id) {
        NewModel newModel = newDao.findOne(id);
        CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
        newModel.setCategoryCode(categoryModel.getCode());
        return newModel;
    }
}
