package com.javaServlet.DAO;

import com.javaServlet.Model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
