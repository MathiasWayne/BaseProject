package cn.itheima.servlet;

import cn.itheima.domain.Manager;
import cn.itheima.domain.PageBean;
import cn.itheima.domain.User;
import cn.itheima.service.UserService;
import cn.itheima.service.userServiceImp.UserServiceImp;
import org.apache.commons.beanutils.BeanUtils;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 18:57
 **/
@WebServlet("/base")
public class BeautifulServlet extends BaseServlet {
    private UserService service=new UserServiceImp();
    //查询所有用户信息
    public  String selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         List<User> list=service.selectAll();
            request.getSession().setAttribute("users",list);
         return "r:"+request.getContextPath()+"/list.jsp";
    }
    //删除用户
   public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       service.delete(request.getParameter("name"));
     return "/base?method=selectAll";
    }

    //添加用户

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        service.add(user);
       return "/base?method=selectAll";
    }

    //通过id查询用户信息
    public String selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先通过userid查询到user所有信息，用作信息回显
        String id = request.getParameter("id");
         User user=service.selectByid(Integer.parseInt(id));
          request.setAttribute("userById",user);
        return "/update.jsp";
    }
    //更新用户信息
    public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        service.update(user);
        return "/base?method=selectAll";
    }



    //查询所有数据，采用分页的形式
    public String select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        if(currentPage==null){
            currentPage="1";
        }
        PageBean<User> list=service.select(Integer.parseInt(currentPage));
        request.setAttribute("list",list);
        return "/list.jsp";
    }

 /* private String getReq(HttpServletRequest request){

  }*/
    //模糊查询所有数据
    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取到请求参数，并封装到User对象中
        User user=new User();
       String name =request.getParameter("name");

        String address =request.getParameter("address");

        String gender =request.getParameter("gender");
        user.setName(name);
        user.setGender(gender);
        user.setAddress(address);
        //获取到请求参数 currentPage
        System.out.println(request.getRequestURL());
        String currentPage = request.getParameter("currentPage");
        int page=Integer.parseInt(currentPage);

        PageBean<User> bean=service.queryCount(user,page);
         //将pagebean保存到request域中
        request.setAttribute("list",bean);
        return "/list.jsp";
    }


}
