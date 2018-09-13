package cn.itheima.dao;

import cn.itheima.domain.Manager;
import cn.itheima.domain.User;

import java.util.List;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 18:28
 **/
public interface UserDao {
    List<User> selectAll();
    //管理员登录
    Manager login(Manager manager);

    void delete(String name);
    //添加新的用户
    void add(User user);
    //根据id查询用户
    User selectByid(int id);
    //更新用户信息
    void update(User user);

    int selectCount();

    List selectRecode(int i, int pageCount);
//查询所有数据量
    int queryAll(User user);

    List queryForList(User user, int page,int pageSize);
}
