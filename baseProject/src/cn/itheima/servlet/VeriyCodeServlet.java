package cn.itheima.servlet;

import utils.VerifyCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 19:54
 **/
@WebServlet(value = "/veriyCodeServlet")
public class VeriyCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int w = 200, h = 80;
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        ServletOutputStream outputStream = response.getOutputStream();
        VerifyCodeUtils.outputImage(w,h,outputStream,verifyCode);

        request.getSession().setAttribute("code",verifyCode.toLowerCase());

    }
}
