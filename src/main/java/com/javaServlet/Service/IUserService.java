package com.javaServlet.Service;

import com.javaServlet.Model.UserModel;

public interface IUserService {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
