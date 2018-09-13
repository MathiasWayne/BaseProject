package cn.itheima.service;

import cn.itheima.domain.Manager;
import cn.itheima.domain.PageBean;
import cn.itheima.domain.User;

import java.util.List;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 18:29
 **/
public interface UserService {
    //查询所有用户信息
    List<User> selectAll();
    //管理员登录
    Manager login(Manager manager);

    void delete(String name);
    //添加新的用户
    void add(User user);

     //根据id查询
    User selectByid(int id);
    //更新用户信息
    void update(User user);
   //查询所有用户，使用分页的方式
    PageBean<User> select(int i);

    PageBean queryCount(User user, int page);
}
