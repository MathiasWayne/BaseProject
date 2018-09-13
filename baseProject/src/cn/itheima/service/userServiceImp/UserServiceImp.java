package cn.itheima.service.userServiceImp;

import cn.itheima.dao.UserDao;
import cn.itheima.dao.userDaoImp.UserDaoImp;
import cn.itheima.domain.Manager;
import cn.itheima.domain.PageBean;
import cn.itheima.domain.User;
import cn.itheima.service.UserService;

import java.util.List;

/**
 * @program: web
 * @description: ${description}
 * @author: AloysMack
 * @create: 2018-09-06 19:00
 **/
public class UserServiceImp implements UserService {
   private UserDao dao=new UserDaoImp();
    @Override
    public List<User> selectAll() {

         List<User> list=dao.selectAll();
        return list;
    }

    @Override
    public Manager login(Manager manager) {
        UserDao m=new UserDaoImp();
          Manager manager1=m.login(manager);
        return manager1;
    }

    @Override
    public void delete(String name) {
        dao.delete(name);
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public User selectByid(int id) {
        return dao.selectByid(id);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public PageBean<User> select(int i) {
        //在业务层分装PageBean对象
        PageBean<User> pageBean=new PageBean<>();
        //每页记录数
        int pageCount=2;
        pageBean.setPageCount(pageCount);
        //当前页数
        pageBean.setCurrentPage(i);
        //总的记录数
         int count=dao.selectCount();
         pageBean.setTotalRecode(count);
        //当前页的记录
        List list=dao.selectRecode(i,pageCount);
        pageBean.setBeanList(list);
        return pageBean;
    }
//查询pagebean
    @Override
    public PageBean queryCount(User user, int page) {
        //封装pageBean
        PageBean<User> pageBean=new PageBean<>();
        pageBean.setCurrentPage(page);
        int pageSize=3;
        pageBean.setPageCount(pageSize);
        //查询模糊查询的总数据量
        int count=dao.queryAll(user);

        pageBean.setTotalRecode(count);

        //获取每页的记录数
        List<User> list=dao.queryForList(user,page,pageSize);
        pageBean.setBeanList(list);
        return pageBean;
    }
}
