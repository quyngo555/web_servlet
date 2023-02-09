package com.javaServlet.Controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaServlet.DAO.INewDAO;
import com.javaServlet.Model.NewModel;
import com.javaServlet.Model.UserModel;
import com.javaServlet.Service.INewService;
import com.javaServlet.Service.impl.NewService;
import com.javaServlet.Utils.HttpUtil;
import com.javaServlet.Utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

    @Inject
    private INewService newService;


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        // mapper json to model
        NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
        newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
        newModel = newService.save(newModel);
        mapper.writeValue(resp.getOutputStream(), newModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel updateNew =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        updateNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
        updateNew = newService.update(updateNew);
        mapper.writeValue(response.getOutputStream(), updateNew);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        NewModel newModel =  HttpUtil.of(request.getReader()).toModel(NewModel.class);
        newService.delete(newModel.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
