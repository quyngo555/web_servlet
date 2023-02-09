package com.javaServlet.Controller.web;

import com.javaServlet.Model.UserModel;
import com.javaServlet.Service.ICategoryService;
import com.javaServlet.Service.IUserService;
import com.javaServlet.Utils.FormUtil;
import com.javaServlet.Utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IUserService userService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            String alert = req.getParameter("alert");
            String message = req.getParameter("message");
            if (message != null && alert != null) {
                req.setAttribute("message", resourceBundle.getString(message));
                req.setAttribute("alert", alert);
            }
            RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
            rd.forward(req, resp);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(req, "USERMODEL");
            resp.sendRedirect(req.getContextPath()+"/trang-chu");
        } else {
            req.setAttribute("categories", categoryService.findAll());
            RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel model = FormUtil.toModel(UserModel.class, request);
            model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
            if (model != null) {
                SessionUtil.getInstance().putValue(request, "USERMODEL", model);
                if (model.getRole().getCode().equals("USER")) {
                    response.sendRedirect(request.getContextPath()+"/trang-chu");
                } else if (model.getRole().getCode().equals("ADMIN")) {
                    response.sendRedirect(request.getContextPath()+"/admin-home");
                }
            } else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
            }
        }
    }
}
