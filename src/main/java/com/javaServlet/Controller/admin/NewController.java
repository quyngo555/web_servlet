package com.javaServlet.Controller.admin;

import com.javaServlet.Constant.SystemConstant;
import com.javaServlet.Model.NewModel;
import com.javaServlet.Paging.PageRequest;
import com.javaServlet.Paging.Pageble;
import com.javaServlet.Service.ICategoryService;
import com.javaServlet.Service.INewService;
import com.javaServlet.Sort.Sorter;
import com.javaServlet.Utils.FormUtil;
import com.javaServlet.Utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {


    @Inject
    private INewService newService;

    @Inject
    private ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewModel model = FormUtil.toModel(NewModel.class, req);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(newService.findAll(pageble));
            model.setTotalItem(newService.getTotalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            view = "/views/admin/new/list.jsp";
        } else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = newService.findOne(model.getId());
            }
            req.setAttribute("categories", categoryService.findAll());
            view = "/views/admin/new/edit.jsp";
        }
        MessageUtil.showMessage(req);
        req.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = req.getRequestDispatcher(view);

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
