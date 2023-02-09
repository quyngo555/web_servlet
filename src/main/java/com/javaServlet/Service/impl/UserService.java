package com.javaServlet.Service.impl;

import com.javaServlet.DAO.IUserDAO;
import com.javaServlet.Model.UserModel;
import com.javaServlet.Service.IUserService;

import javax.inject.Inject;



public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
    }
}
