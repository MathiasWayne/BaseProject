package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author AloysMack
 * utils.BaseServlet 工具类实现可以根据不同的menthod参数 处理不同的请求
 **/
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String method = req.getParameter("method");
        //如果没有给出参数，则给出错误，提示需要提供方法名称
         if (method==null){
            throw new RuntimeException("给出执行参数method");
         }
        //通过反射来调用体内的洪荒之力
        Class<? extends BaseServlet> clazz = this.getClass();
        Method m;
        try {
             m = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("未找到方法");
        }
        //执行当前的方法
        try {
           String str= (String)m.invoke(this,req,resp);

           //截取返回值，并对字符串进行截取 执行跳转和转发
           if (str==null){
            //如果返回值为null，return null 既不转发也不跳转
               return;
           }else{
               //返回值进行截取
               if (str.contains(":")){
                   int i = str.indexOf(":");
                   //得到冒号之前的字符，即决定是重定向还是转发 r为重定向 f为转发
                   String substring = str.substring(0, i);
                   //冒号之后的为转发或者是重定向的路径
                   String sb = str.substring(i+1);

                    if (Objects.equals(substring,"r")){
                        //重定向
                        resp.sendRedirect(sb);
                    }
                    else if (Objects.equals("f",substring)){
                        //转发
                        req.getRequestDispatcher(sb).forward(req,resp);
                    }else{
                        throw new RuntimeException("路径存在问题");
                    }
               }
               else{
                   //如果返回值中不存在“:”,则说明是转发
                   req.getRequestDispatcher(str).forward(req,resp);
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("该方法内部抛出异常");
        }
    }
}
