package com.javaServlet.Utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
    public static  <T>  T toModel(Class<T> clazz, HttpServletRequest request){
        T object = null;
        try {
            object = clazz.newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.print(e.getMessage());
        }
        return object;
    }
}
