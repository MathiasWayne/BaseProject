package cn.itheima.servlet;

import cn.itheima.domain.Manager;
import cn.itheima.service.UserService;
import cn.itheima.service.userServiceImp.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-07 10:08
 **/
@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //先判断验证码是否正确，正确则向下执行，若不正确则直接转发到登录页面
        String name=request.getParameter("user");
        String password = request.getParameter("password");
        String code = (String)request.getSession().getAttribute("code");
        String verifycode = request.getParameter("verifycode");
        if (!Objects.equals(code,verifycode)){
            request.setAttribute("name",name);
            request.setAttribute("password",password);
            request.setAttribute("msg","验证码错误，请重新输入");
            request.getSession().removeAttribute("code");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            //return "/login.jsp";
            return;
        }
        UserService service=new UserServiceImp();
        Manager manager=new Manager();
        manager.setName(name);
        manager.setPassword(password);
        request.getSession().removeAttribute("code");
        try{
            //如果查找了，则说明用户名和密码正确，想session域中保存数据
            Manager manager1=service.login(manager);
            request.getSession().setAttribute("manager",manager1);
          request.getRequestDispatcher("/base?method=select").forward(request,response);
           // response.sendRedirect(request.getContextPath()+"/base?method=selectAll");
            // return "r:"+request.getContextPath()+"/index.jsp";
        }catch (Exception e){
            //如果有异常则说明，用户名和密码错，转发回login页面
            request.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            // return "/login.jsp";
        }
    }
}
